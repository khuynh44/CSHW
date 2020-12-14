#include "project4.h"

//Array of possible documents
doc_t doc_system[MAX_NUM_DOCS];
//bit vector representing the validity of each doc entry
uint64_t doc_valid_vector;
//command line arguments for each user command
char *arguments[4];
//data array (space pre-allocated for document contents)
char data[MAX_DOCSIZE * MAX_NUM_DOCS];

/**
 * @brief Creates a new document in our system if a document of the same name doesn't exist already. 
 * If docname is NULL, a document with the same name already exists, or there is no more space to create documents, do nothing and return -1
 * 
 * @param docname the name of the doc to create, including the extension
 * @return the index of the new document, or -1 in case of error
 */
int new_doc(char *docname) {
    if (docname == NULL || find_doc(docname) != -1 || GET_DOC_PRESENT(doc_valid_vector, 63)) {
        return ERROR;
    }
    int index = 0;
    while (GET_DOC_PRESENT(doc_valid_vector, index)) {
        index++;
    }
    char name[MAX_NAMESIZE];
    ext_t ext;
    get_name_ext(name, &ext, docname);
    strcpy(doc_system[index].name, name);
    doc_system[index].extension = ext;
    doc_system[index].permissions = 7;
    doc_system[index].data = &data[MAX_DOCSIZE*index];
    doc_system[index].index = index;
    doc_valid_vector = SET_DOC_PRESENT(doc_valid_vector, index);
    UNUSED(docname);
    return index;
}

/**
 * @brief Find a doc in the doc system array given its name, including the extension
 * If docname is NULL or the document is not present, return -1
 * 
 * @param docname the name of the doc to find, including the extension
 * @return index referring to the document in the doc_system, -1 on error
 */
int find_doc(const char *docname) {
    if (docname == NULL) {
        return ERROR;
    }
    char name[MAX_NAMESIZE];
    ext_t ext;
    get_name_ext(name, &ext, docname);
    for (int i = 0; i < MAX_NUM_DOCS; i++) {
        if (strcmp(doc_system[i].name, name) == 0 && ext == doc_system[i].extension) {
           return i;
        }
    }
    return ERROR;
}

/**
 * @brief List all doc names in the doc array. Only list those that are marked as
 * valid by the doc valid vector. The helper function print_doc_name will be useful.
 * 
 */
void list(void) {
    for (int i = 0; i < MAX_NUM_DOCS; i++) {
        if (GET_DOC_PRESENT(doc_valid_vector, i)) {
            print_doc_name(doc_system[i]);
        }
    }
}

/**
 * @brief Append a string to a doc. This operation fails if the string is too large to append to the doc
 * given the length of the string, the current size of the doc, and the max doc size constant. Remember that
 * the data for each document must be zero-terminated. This operation also fails if doc or str is NULL, or if the doc to append to does not have write permissions enabled.
 * 
 * @param doc the doc to append to
 * @param str the string to append to the doc
 * @return an int representing the success of the operation. 0 if successful, -1 otherwise.
 */
int append(doc_t *doc, char *str) {
    if (doc == NULL || str == NULL || ((doc->permissions)&2) == 0 || (int)(strlen(str)) > (MAX_DOCSIZE - 1 - (doc->size))) {
        return ERROR;
    }
    strcpy(((doc->data)+(doc->size)), str);
    doc->size += (int)(strlen(str));
    return SUCCESS;
}

/**
 * @brief Import the contents of an external file into this doc system.
 * This function should create a new document to import the contents into.
 * Fails if the contents of the file, with the zero-terminator added at the end, are larger than the max doc size,
 * there is not enough space for a new doc, or if docname or file is NULL.
 * 
 * @param docname the name of the new document to create, including the extension
 * @param file the file pointer to read data from
 * @return an int representing the success of the operation. 0 if successful, -1 otherwise.
 */
int import(char *docname, FILE *file) {
    if (docname == NULL || file == NULL || MAX_DOCSIZE < get_file_size(file) || GET_DOC_PRESENT(doc_valid_vector, 63)) {
        return ERROR;
    }
    int index = new_doc(docname);
    char buffer[MAX_DOCSIZE];
    fread(buffer, get_file_size(file), 1, file);
    append(&doc_system[index], buffer);
    return SUCCESS;
}

/**
 * @brief Prints the internal data of the given doc, followed by a newline.
 * Fails if doc is NULL, or if the doc does not have read permissions enabled.
 * 
 * @param doc the doc whose data we print
 * @return an int representing the success of the operation. 0 if successful, -1 otherwise.
 */
int print_doc_data(const doc_t *doc) {
    if (doc == NULL || (doc->permissions&4) == 0) {
        return ERROR;
    }
    printf("%s\n", doc->data);
    UNUSED(doc);
    return SUCCESS;
}

/**
 * @brief Export the contents of a doc in our system to an external file.
 * Fails if doc or file is NULL or if the doc does not have read permissions enabled.
 * 
 * @param doc the doc whose data we export
 * @param file the file to export to
 * @return an int representing the success of the operation. 0 if successful, -1 otherwise.
 */
int export(const doc_t *doc, FILE *file) {
    if (doc == NULL || file == NULL || (doc->permissions&4) == 0) {
        return ERROR;
    }
    fprintf(file, "%s", doc->data);
    return SUCCESS;
}

/**
 * @brief Remove the given doc from the doc system. This doc, if not NULL, is guaranteed to exist in the system.
 * Fails if doc was NULL.
 * 
 * @param doc the doc to remove
 * @return an int representing the success of the operation. 0 if successful, -1 otherwise.
 */
int remove_doc(doc_t *doc) {
    if (doc == NULL) {
        return ERROR;
    }
    int index = doc->index; 
    if (strcmp(doc_system[index].name, doc->name) == 0) {
        doc_valid_vector = CLEAR_DOC_PRESENT(doc_valid_vector, index);
    }
    return SUCCESS;
}

/**
 * @brief Change the read, write, execute permissions of a doc
 * Fails if doc or mode_changes is NULL.
 * 
 * @param doc the doc to modify the permissions of
 * @param mode_change the string representing the changes to be made (i.e. '+rw' or '-x')
 * @return an int representing the success of the operation. 0 if successful, -1 otherwise.
 */
int change_mode(doc_t *doc, char *mode_changes) {
    if (doc == NULL || mode_changes == NULL) {
        return ERROR;
    }
    if (*(mode_changes) == '+') {
        for (int i = 1; i < (int)(strlen(mode_changes)); i++) {
            if (*(mode_changes + i) == 'r') {
                doc->permissions = (doc->permissions)|4;
            } else if (*(mode_changes + i) == 'w') {
                doc->permissions = (doc->permissions)|2;
            } else {
                doc->permissions = (doc->permissions)|1;
            }
        }
    } else {
        for (int i = 1; i < (int)(strlen(mode_changes)); i++) {
            if (*(mode_changes + i) == 'r') {
                doc->permissions = (doc->permissions)&(~4);
            } else if (*(mode_changes + i) == 'w') {
                doc->permissions = (doc->permissions)&(~2);
            } else {
                doc->permissions = (doc->permissions)&(~1);
            }
        }
    }
    return SUCCESS;
}



/**
 * @brief Main method that waits for user input to modify the docsystem. This method starts
 * up a mockup shell and tokenizes each user command using the tokenize helper method.
 * 
 * For example, if the user inputs: 
 * >> cmd name.doc other.txt
 * 
 * The global arguments array will be populated as : {"cmd", "name.doc", "other.txt"}
 * You may use the arguments array to get the arguments for whatever operation needs to be performed.
 * 
 * Note: the command to perform (i.e. cp, new, exit...) will always be in arguments[0]
 * 
 * @param argc unused int
 * @param argv unused array of strings
 * @return integer representing the success of the main method (0 on exit, 1 otherwise)
 */
int my_main(int argc, char const *argv[]) {
    UNUSED(argc);
    UNUSED(argv);
    char *shell_string = ">> ";

    //set up buffer to read in command line input
    char buffer[MAX_CMD_LEN]; 

    //will loop infinitely unless the user inputs 'exit'
    while(1) {
        printf("%s", shell_string);
        //get user input into buffer and tokenize it using the tokenize method
        fgets(buffer, MAX_CMD_LEN, stdin);
        tokenize(buffer);
        char *cmd = arguments[0];

        //TODO: Add new cases for different commands. You can follow the example given for new.
        if (strncmp(cmd, "new", 3) == 0) {
            char *name = arguments[1];
            new_doc(name); 
        } 
        else if (strncmp(cmd, "exit", 4) == 0) {
            return 0;
        } else if (strncmp(cmd, "ls", 2) == 0) {
            list();
        } else if (strncmp(cmd, "import", 6) == 0) {
            FILE *file;
            char *filename = arguments[1];
            char *docname = arguments[2];
            file = fopen(filename, "r");
            import(docname, file);
            fclose(file);
        } else if (strncmp(cmd, "export", 6) == 0) {
            FILE *file;
            char *filename = arguments[2];
            char *docname = arguments[1];
            int index = find_doc(docname);
            file = fopen(filename, "w");
            export(&doc_system[index], file);
            fclose(file);
        } else if (strncmp(cmd, "print", 5) == 0) {
            char *docname = arguments[1];
            int index = find_doc(docname);
            if (index == -1) {
                continue;
            }
            print_doc_data(&doc_system[index]);
        } else if (strncmp(cmd, "append", 6) == 0) {
            char *docname = arguments[1];
            int index = find_doc(docname);
            if (index == -1) {
                continue;
            }
            char *string = arguments[2];
            append(&doc_system[index], string);
        } else if (strncmp(cmd, "chmod", 5) == 0) {
            char *docname = arguments[1];
            char *mode_changes = arguments[2];
            int index = find_doc(docname);
            if (index == -1) {
                continue;
            }
            change_mode(&doc_system[index], mode_changes);
        } else if (strncmp(cmd, "rm", 2) == 0) {
            char *docname = arguments[1];
            int index = find_doc(docname);
            if (index == -1) {
                continue;
            }
            remove_doc(&doc_system[index]);
        }
    }
    return 1;
}

