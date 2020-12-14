package PE04;

public class Pizza {
    private int numSlices; 
    private String[] toppings;
    private boolean hasCheese;
    private boolean isGlutenFree;
    
    public Pizza(int slices, boolean cheese, boolean gluten, String[] toppings) { 
        numSlices = slices; 
        hasCheese = cheese; 
        isGlutenFree = gluten; 
        this.toppings = toppings; 
    }
    public Pizza() { 
    	numSlices = 8; 
    	toppings = new String[0];
    	hasCheese = true; 
    	isGlutenFree = false; 
    }
    public String[] getToppings() { 
    	return toppings;
    }
    public void setToppings(int num) { 
        toppings = new String[num];
    }
    public int getNumSlices() { 
        return numSlices;
    }
    public void setNumSlices(int slices) {
        numSlices = slices;
    }
    public void setHasCheese(boolean cheese) { 
        hasCheese = cheese;
    }
    public void setIsGlutenFree(boolean gluten) { 
    	isGlutenFree = gluten; 
    }
    public void printToppings() { 
    	for(int i = 0; i < toppings.length; i++) { 
    	    System.out.println("{" + toppings[i] + "}");
    	}
    }
    public void changeFirstTopping(String topping) { 
        toppings[0] = topping;
    }
}
