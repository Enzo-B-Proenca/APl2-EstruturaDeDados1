// Nome: Bruno Antico Galin	| RA: 10417318 
// Nome: Enzo Benedetto Proença | RA: 10418579 
// Nome: Gabriel Alves de Freitas Spinola Sucupira | RA: 10418133
// Nome: Ismael de Sousa e Silva | RA: 10410870 
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=N6dOwBde7-M
// Referência: https://www.youtube.com/watch?v=VJgCjLuU4e8&list=PLqleLpAMfxGDVu5tUmUg9jSQUUB8_5DB0
public class node{
  private node Next;
  private node Previous;
  private String data;
  private boolean marca = false;

  // Construtor vazio tendo todos como null
  public node(){
    this.Next = null;
    this.Previous = null;
    this.data = null;
  }

  // Construtor apenas com data como valor	
  public node(String data){
    this.Next = null;
    this.Previous = null;
    this.data = data;
  }

  // Construtor com data e o nó anterior	
  public node(node Previous, String data){
    this.Next = null;
    this.Previous = Previous;
    this.data = data;
  }

  // Construtor com todos os dados 
  public node(node Previous, String data, node Next){
    this.Next = Next; 
    this.Previous = Previous;
    this.data = data;	
  }

  // Getter para os dados
  public String getData(){
    return data;
  }

  // Setter para os dados
  public void setData(String data){
    this.data = data;
  }

  // Getter para o nó anterior
  public node getPrevious(){
    return Previous;
  }

  // Setter para o nó anterior
  public void setPrevious(node Previous){
    this.Previous = Previous;
  }

  // Getter para o próximo nó
  public node getNext(){
    return Next;
  }

  // Setter para o próximo
  public void setNext(node Next){
    this.Next = Next;
  }

  public void MarcarNo() {
    this.marca = true;
  }

  public boolean getMarca() {
    return marca;
  }
  
  public void desMarcarNo() {
	  this.marca = false;
  }

  @Override
  public String toString() {
    return "{ " +  data
          + ", previous: " + (Previous != null ? Previous.getData() : "null")
          + ", next: " + (Next != null ? Next.getData() : "null") + " }";
  }
  
}
