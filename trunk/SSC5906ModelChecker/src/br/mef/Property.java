package br.mef;

public class Property {
	
	private String name;

	public Property (String name){
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    @Override
    public boolean equals(Object prop){
        try{
        	return (this.getName().equals(((Property) prop).getName()));
        }
        catch(java.lang.RuntimeException e){
            return false;
        }

    }	

}
