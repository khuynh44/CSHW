/**
 * CS 2110 - Fall 2019 - Project 5
 *
 * @author Elton Pinto
 *
 * list.c: Complete the functions!
 */

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!-IMPORTANT-!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * For any function that must use malloc, if malloc returns NULL, the function
 * itself should return NULL if needs to return something (or return 0 if
 * the function returns a int).
 */

// Do not add ANY additional includes!!!
#include "list.h"
// This is used so that you guys can print stuff out
#include <stdio.h>

/* You should NOT have any global variables. */


/** create_process
 *
 * Helper function that creates a struct process by allocating memory for it on the heap
 *
 * If malloc returns NULL, you should return NULL to indicate failure
 *
 * The pid of the process should be determined automatically. The first
 * process created should have a pid of 0, and each future one should have
 * a pid of one higher than the previous.
 * 
 * @param name A pointer to char that points to the name of the process
 * @param priority The priority of the process
 * @return a struct process
 */
Process *create_process(char *name, int priority) {
    Process *newProcess = (Process *)malloc(sizeof(Process));
    if (newProcess == NULL) {
        return NULL;
    }
    newProcess->name = (char *)malloc(sizeof(name));
    static int pid = 0;
    strcpy(newProcess->name, name);
    newProcess->priority = priority;
    newProcess->pid = pid++;
    return newProcess;
}


/** create_node
 *
 * Helper function that creates a struct list_node by allocating memory for it on the heap.
 * Be sure to initialize its next pointer to NULL and prev pointer to NULL.
 *
 * If malloc returns NULL, you should return NULL to indicate failure.
 *
 * @param data a void pointer to data the user wants to store in the struct list
 * @return a struct list_node
 */
ListNode *create_node(Process *data) {
    ListNode *node = (ListNode *)malloc(sizeof(ListNode));
    if (node == NULL) {
        return NULL;
    }
    node->data = data;
    node->next = NULL;
    node->prev = NULL;
    return node;
}


/** create_list
 *
 * Creates a struct list by allocating memory for it on the heap.
 * Be sure to initialize head to NULL.
 *
 * If malloc returns NULL, you should return NULL to indicate failure.
 *
 * @return a pointer to a new struct list or NULL on failure
 */
List *create_list(void)
{
    List *list = (List *)malloc(sizeof(List));
    if (list == NULL) {
        return NULL;
    }
    list->head = NULL;
    return list;
}


/** push_front
 *
 * Adds a ListNode to the front of the list
 *
 * @param list a pointer to the List structure
 * @param node The node to add to the list
 * @return
 *      0, if
 *          - the list is NULL
 *          - the node is NULL (do not add the data in this case)
 *      1, otherwise
 *
 * Note: If the data in the node is NULL, you must add it to the list
 */
int push_front(List *list, Process *node) {
    ListNode *newNode = create_node(node);
    if (list == NULL || newNode == NULL) {
        free(newNode);
        return 0;
    }
    ListNode *temp = list->head;
    list->head = newNode;
    list->head->next = temp;
    list->head->prev = NULL;
    return 1;
}


/** push_back
 *
 * Adds the ListNode to the back of the list
 *
 * @param list a pointer to the List structure
 * @param node the node to add to the list
 * @return
 *      0, if
 *          - the list is NULL
 *          - the node is NULL (do not add the data in this case)
 *      1, otherwise
 *
 * Note: If the data in the node is NULL, you must add it to the list
 */
int push_back(List *list, Process *node) {
    ListNode *newNode = create_node(node);
    if (list == NULL || newNode == NULL) {
        free(newNode);
        return 0;
    }
    ListNode *temp = list->head;
    if (temp == NULL) {
        list->head = newNode;
        return 1;
    }
    while (temp->next != NULL) {
        temp = temp->next;
    }
    temp->next = newNode;
    newNode->prev = temp;
    return 1;
}

/** remove_node
  *
  * Remove the first node with the passed in `pid`
  *
  * The node should be removed completely, but the node's data should be returned through the `dataOut` parameter
  *
  * @param list a pointer to the struct list structure
  * @param dataOut a double pointer to return the Process data that is being removed from the list
  * @param pid The pid of the process that you want to remove
  * @return
  *     0, if
  *         - the struct list is NULL
  *         - there is no process with the specified pid
  *         - the dataOut is NULL
  *     1, otherwise
  */
int remove_node(List *list, Process **dataOut, int pid) {
    if (list == NULL || dataOut == NULL) {
        return 0;
    }
    ListNode *temp = list->head;
    if (temp == NULL) {
        dataOut = NULL;
        return 1;
    }
    while (temp != NULL) {
        if (temp->data->pid == pid) {
            ListNode *next = temp->next;
            ListNode *prev = temp->prev;
            if (next == NULL && prev == NULL) {
                list->head = NULL;
            } else if (next == NULL) {
                prev->next = NULL;
            } else if (prev == NULL) {
                next->prev = NULL;
                list->head = next;
            } else {
                next->prev = prev;
                prev->next = next;
            }
            *dataOut = temp->data;
            free(temp);
            return 1;
        }
        temp = temp->next;
    }
    return 0;
}

/** destroy_process
 *
 * Destroys all data in the process. Make sure to free any process attributes that have memory allocated for them.
 *
 * @param proc The process to destroy
*/
void destroy_process(Process *proc) {
    if (proc == NULL) {
        return;
    }
    if (proc->name != NULL) {
        free(proc->name);
    }
    free(proc);
}

/** destroy
  *
  * Destroys the list. This frees the list, nodes within the list, and data within the nodes
  * (`destroy_proc` can help with free'ing the data within the nodes).
  *
  * Make sure to check that the list is not NULL
  *
  * @param list A pointer to the struct list structure
  */
void destroy(List *list)
{
    if(list == NULL) {
        return;
    }
    if (list->head != NULL) {
        ListNode *curr = list->head;
        list->head = curr->next;
        destroy(list);
        destroy_process(curr->data);
        free(curr);
    } else {
        free(list);
    }
}

/** copy_processs
 *
 * A function that will **deep** copy the process and all its attributes.
 *
 * For any malloc failures, you must not leak memory. This means that
 * if any memory failures occur, before you return 0, you must go back
 * and free any data that you allocated memory for.
 *
 *
 * @param from The process to copy
 * @param to The address of the pointer that will point to the copy
 *           of the process
 * @return
 *      0, if
 *          - malloc fails
 *          - from is NULL
 *          - to is NULL
 *      1, otherwise
 */
int copy_process(Process *from, Process **to) {
    if (from == NULL || to == NULL) {
        return 0;
    }
    *to = (Process *)malloc(sizeof(Process));
    if (*to == NULL) {
        return 0;
    }
    char *name = (char *)malloc(sizeof(from->name));
    if (name == NULL) {
        free(*to);
        return 0;
    }
    strcpy(name, from->name);
    (*to)->name = name;
    (*to)->pid = from->pid;
    (*to)->priority = from->priority;
    return 1;
}

/** copy_list
 *
 * Create a new list structure, new nodes, and new deep copies of the data
 * (use the copy_process function for this).
 *
 * If listToCopy is NULL or empty return NULL. For memory allocations
 * failures, including if copy_process returns 0 (indicating a memory
 * allocation failure), your code must not leak memory. This means that if any
 * memory allocation failures occur, before you return NULL, you must go back
 * and free all data in the new list, nodes in the new list, and
 * the new list itself (you may find the destroy function useful for
 * this).
 *
 *
 * @param listToCopy A pointer to the struct list structure to make a copy of
 * @return The list structure created by copying the old one, or NULL on
 *         failure (includes any of the parameters being null)
 */
List *copy_list(List *listToCopy)
{
    List *list = create_list();
    if (listToCopy == NULL || listToCopy->head == NULL || list == NULL) {
        free(list);
        return NULL;
    }
    ListNode *curr = listToCopy->head;
    while (curr != NULL) {
        Process *process;
        if (copy_process(curr->data, &process) && push_back(list, process)) {
            curr = curr->next;
        } else {
            destroy(list);
            return NULL;
        }
    }
    return list;
}

/** compare_process
 *
 * This method compares the pids of two processes
 * A null process has a larger pid than a non-null process
 *
 * @param a A process
 * @param b Another process
 * @return >0 if process a's pid > process b's pid
 *          0 if process a's pid = process b's pid
 *         <0 if process a's pid < process b's pid
 **/
// TODO doc
int compare_pid(Process *a, Process *b) {
    if (a == NULL && b == NULL) {
        return 0;
    } else if (a == NULL) {
        return 1;
    } else if (b == NULL) {
        return -1;
    } else {
        return (a->pid) - (b->pid);
    }
}

/** compare_name
 *
 * This method compares the name of two processes
 * A null process has a larger name than a non-null process
 * (Hint) Take a look at the strcmp man page
 *
 * @param a A process
 * @param b Another process
 * @return >0 if process a's name > process b's name
 *          0 if process a's name = process b's name
 *         <0 if process a's name < process b's name
 **/
int compare_name(Process *a, Process *b) {
    if (a == NULL && b == NULL) {
        return 0;
    } else if (a == NULL) {
        return 1;
    } else if (b == NULL) {
        return -1;
    } else {
        return strcmp(a->name, b->name);
    }
}

/** swap_nodes
 *
 * This method swaps two nodes in place. For example if our list was
 * A -> B -> C -> D and we called swap_nodes(b, d, list), the resulting list would be
 * A -> D -> C -> B
 * Note: The nodes will either be NULL or will exist in the list
 *
 * @param node_a A node to swap
 * @param node_b The other node to swap
 * @param list The list within which the nodes will be swapped
 * @return 0, if
 *              - any of the parameters are null
 *              - node_a = node_b
 *         1, otherwise
**/
int swap_nodes(ListNode *node_a, ListNode *node_b, List *list) {
    if (node_a == NULL || node_b == NULL || list == NULL || node_a == node_b) {
        return 0;
    }
    ListNode *aprev = node_a->prev;
    ListNode *anext = node_a->next;
    ListNode *bprev = node_b->prev;
    ListNode *bnext = node_b->next;
    if (aprev == NULL) {
        if (anext == node_b) {
            node_a->next = bnext;
            node_b->prev = NULL;
            node_b->next = node_a;
            node_a->prev = node_b;
            if (bnext != NULL) {
                bnext->prev = node_a;
            }
            list->head = node_b;
            return 1;
        }
        anext->prev = node_b;
        bprev->next = node_a;
        list->head = node_b;
        if (bnext != NULL) {
            bnext->prev = node_a;
        }
    } else if (bprev == NULL) {
        if (bnext == node_a) {
            node_b->next = anext;
            node_a->prev = NULL;
            node_a->next = node_b;
            node_b->prev = node_a;
            if (anext != NULL) {
                anext->prev = node_b;
            }
            list->head = node_a;
            return 1;
        }
        bnext->prev = node_a;
        aprev->next = node_b;
        list->head = node_a;
        if (anext != NULL) {
            anext->prev = node_b;
        }
    } else if (anext == NULL) {
        if (aprev == node_b) {
            node_a->next = node_b;
            node_a->prev = bprev;
            node_b->next = NULL;
            node_b->prev = node_a;
            bprev->next = node_a;
            return 1;
        }
        aprev->next = node_b;
        bprev->next = node_a;
        bnext->prev = node_a;
    } else if (bnext == NULL) {
        if (bprev == node_a) {
            node_b->next = node_a;
            node_b->prev = aprev;
            node_a->next = NULL;
            node_a->prev = node_b;
            aprev->next = node_b;
            return 1;
        }
        bprev->next = node_a;
        aprev->next = node_b;
        anext->prev = node_b;
    } else {
        if (anext == node_b) {
            node_a->next = bnext;
            node_a->prev = node_b;
            node_b->next = node_a;
            node_b->prev = aprev;
            bnext->prev = node_a;
            aprev->next = node_b;
            return 1;
        } else if (bnext == node_a) {
            node_b->next = anext;
            node_b->prev = node_a;
            node_a->next = node_b;
            node_a->prev = bprev;
            anext->prev = node_b;
            bprev->next = node_a;
            return 1;
        }
        anext->prev = node_b;
        aprev->next = node_b;
        bnext->prev = node_a;
        bprev->next = node_a;
    }
    node_a->next = bnext;
    node_a->prev = bprev;
    node_b->next = anext;
    node_b->prev = aprev;
    return 1;
}

/** sort
 *
 * A function to sort the nodes in ascending order (in place) using the comparator function passed in.
 * Recommend using bubble sort (Hint: Utilize the swap_nodes() function above)
 *
 * @param list The list to sort
 * @param compare_func A function pointer that denotes which compare method (written above) will be utilized.
 *                     Assume that the return value of the function pointer will be the return value of the comparator methods
 *                     written above
 * @return
 *      0, if
 *          - list is null
 *          - the list is empty
 *          - compare_func is null
 *      1, otherwise
 */
int sort(List *list, int (*compare_func)(Process *a, Process *b)) {
    if (list == NULL || compare_func == NULL || list->head == NULL ) {
        return 0;
    }
    ListNode *curr;
    int isSorted = 0;
    while(!isSorted) {
        isSorted = 1;
        curr = list->head;
        while(curr->next != NULL) {
            if (compare_func(curr->data, curr->next->data) > 0) {
                swap_nodes(curr, curr->next, list);
                isSorted = 0;
            } else {
                curr = curr->next;
            }
        }
    }
    return 1;
}

/** list_to_array
 *
 * This function will create and utilize a new structure to represent our processes.
 * First, create an array of process pointers, one for each process in the linked list
 * Next, iterate through the linked list, setting the process pointer to its new home in the array
 * and removing the linked list structure (Hint: You want to free the linked list but not the process itself)
 * Finally, return the process array
 *
 * @param list The linked list to be converted to an array
 * @param size The number of processes in the linked list
 * @return
 *      NULL, if
 *              - the list is null
 *              - the list is empty
 *              - there is a malloc failure
 *      An array to process pointers, otherwise
 */
Process **list_to_array(List *list, int size) {
    Process **arr = (Process **)malloc(sizeof(Process)*size);
    if(list == NULL || list->head == NULL || arr == NULL) {
        free(arr);
        return NULL;
    }
    ListNode *curr;
    for (int i = 0; i < size; i++) {
        curr = list->head;
        arr[i] = curr->data;
        list->head = curr->next;
        free(curr);
    }
    free(list);
    return arr;
}


/** make_idle
 *
 * Append the string " (idle)" to the end of the name
 * of the process passed in.
 *
 * If the string " (idle)" already exists, do not include it
 *
 * You **must** use `realloc`.
 * You **cannot** use any functions in <string.h>
 *
 * @param proc A pointer to a Process struct
 * @return
 *      0, if
 *          - proc is NULL
 *          - the name of the proc is NULL
 *          - any other failure (for example, if `realloc` fails)
 *      1, otherwise
 */
int make_idle(Process *proc) {
    if (proc == NULL || proc->name == NULL) {
        return 0;
    }
    if(strlen(proc->name) > 7) {
        char *buffer = proc->name + strlen(proc->name) - 7;
        if (strcmp(buffer, " (idle)") == 0) {
            return 1;
        }
    }
    
    char *temp = realloc(proc->name, strlen(proc->name) + 8);
    if (temp == NULL) {
        return 0;
    }
    proc->name = strcat(temp, " (idle)");
    return 1;
}

/** make_active
 *
 * Remove the string " (idle)" from the end of the name of
 * of the process passed in, if it exists
 *
 * You **must** use `realloc`.
 * You **cannot** use any functions in <string.h>
 *
 * @param proc A pointer to a Process struct
 * @return
 *      0, if
 *          - proc is NULL
 *          - the name of the proc is NULL
 *          - any other failure (for example, if `realloc` fails)
 *      1, otherwise
 */
int make_active(Process *proc) {
    if (proc == NULL || proc->name == NULL) {
        return 0;
    }
    if (strlen(proc->name) <= 7) {
        return 1;
    }
    char *buffer = proc->name + strlen(proc->name) - 7;
    if (strcmp(buffer, " (idle)") == 0) {
        *buffer = '\0';
        char *temp = realloc(proc->name, strlen(proc->name) + 1);
        if (temp == NULL) {
            return 0;
        }
        //*(temp + strlen(temp) - 7) = '\0';
        proc->name = temp;
    }
    return 1;
}

/** apply
 *
 * Invokes `func` on each process of the linked list
 *
 * @param list A pointer to the list structure
 * @param func A pointer to the function to be invoked on each node
 * @return
 *      0, if
 *          - list is NULL
 *          - func is NULL
 *      1, otherwise
 */
int map_inplace(List *list, int (*map_func)(Process *)) {
    if (list == NULL || map_func == NULL) {
        return 0;
    }
    ListNode *curr = list->head;
    while(curr != NULL) {
        map_func(curr->data);
        curr = curr->next;
    }
    return 1;
}
