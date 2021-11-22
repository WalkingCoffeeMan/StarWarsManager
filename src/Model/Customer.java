package Model;

public class Customer 
{
	private int id;
	private String vorname;
	private String nachname;
	private String side;
	
	
	
	public Customer(int id, String vorname,String nachname,String side)
	{
		setId(id);
		setVorname(vorname);
		setNachname(nachname);
		setSide(side);
	}

	public String getSide() 
	{
		return side;
	}



	public void setSide(String side) 
	{
		if(side != null)
		{
			this.side = side;
		}
		else
		{
			System.out.println("Bitte geben Sie die Seite des Kunden an !");
		}
	}



	public String getVorname()
	{
		return vorname;
	}



	public void setVorname(String vorname) 
	{
		this.vorname = vorname;
	}



	public String getNachname() 
	{
		return nachname;
	}



	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
