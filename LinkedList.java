
// Nome: Bruno Antico Galin	| RA: 10417318 
// Nome: Enzo Benedetto Proença | RA: 10418579 
// Nome: Gabriel Alves de Freitas Spinola Sucupira | RA: 10418133
// Nome: Ismael de Sousa e Silva | RA: 10410870 
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=N6dOwBde7-M
// Referência: https://www.youtube.com/watch?v=VJgCjLuU4e8&list=PLqleLpAMfxGDVu5tUmUg9jSQUUB8_5DB0
import java.io.*;

public class LinkedList {
  private node head;
  private node tail;
  private int count;

  public LinkedList() {
    head = tail = null;
    count = 0;
  }

  public void insert(String value) {
    node node = new node(null, value, head);

    if (isEmpty()) {
      tail = node;
    } else {
      head.setPrevious(node);
      node.setNext(head);
    }

    head = node;
    ++count;

    head.setPrevious(tail);
    tail.setNext(head);
  }

  public void append(String value) {
    node node = new node(null, value, null);
    node.desMarcarNo();
    if (isEmpty()) {
      head = node;
    } else {
      tail.setNext(node);
      node.setPrevious(tail);
    }

    tail = node;
    ++count;

    head.setPrevious(tail);
    tail.setNext(head);
  }

  public node removeHead() {
    if (isEmpty()) {
      return null;
    }

    node removed = head;

    if (head == tail) {
      head = tail = null;
    } else {
      head = head.getNext();
      head.setPrevious(tail);
      tail.setNext(head);
    }

    --count;

    removed.setPrevious(null);
    removed.setNext(null);
    return removed;
  }

  public node removeTail() {
    if (head == tail) {
      return removeHead();
    }

    node removed = tail;

    tail = tail.getPrevious();
    head.setPrevious(tail);
    tail.setNext(head);

    --count;

    removed.setPrevious(null);
    removed.setNext(null);
    return removed;
  }

  public node getHead() {
    return head;
  }

  public node getTail() {
    return tail;
  }

  public int count() {
    return count;
  }

  public boolean isEmpty() {
    return head == null && tail == null;
  }

  public void read(String archive) {
    try {
      File file = new File(archive);
      BufferedReader buff = new BufferedReader((new FileReader(file)));
      String lin = buff.readLine();
      while (lin != null) {
        append(lin);
        lin = buff.readLine();
      }
      buff.close();
      System.out.println("Arquivo lido.");
    } catch (IOException e) {
      System.out.println("Erro ao abrir o arquivo.");
    }
  }

  public void save(String archive) {
    try {
      FileWriter w = new FileWriter(archive);
      node aux = head;
      do {
        w.write(aux.getData() + "\n");
        aux = aux.getNext();
      } while (aux != head);
      w.close();
      System.out.println("Arquivo salvo.");
    } catch (IOException e) {
      System.out.println("Erro ao abrir o arquivo.");
    }
  }

  public node removeAt(int index) {
    node current = head;
    try {
      if (isEmpty() || index < 0 || index > count)
        throw new Exception("Linha não encontrada");

      // Remoção do head
      if (index == 0)
        return removeHead();

      // Remoção do tail
      if (index == count - 1)
        return removeTail();

      // Percorre até o node na posição index
      for (int i = 0; i < index; i++)
        current = current.getNext();

      node previous = current.getPrevious();
      node next = current.getNext();

      previous.setNext(next);
      next.setPrevious(previous);

    } catch (Exception e) {
      System.out.println(e);
    }
    return current;
  }

  public node removeUntil(int index) {
    node current = head;
    try {
      if (isEmpty() || index < 0 || index > count)
        throw new Exception("Linha não encontrada");

      for (int i = 0; i < index; ++i) {
        current = current.getNext();
      }
      node startNode = current;
      node previousNode = startNode.getPrevious();
      node endNode = tail;

      do {
        node nextNode = current.getNext();
        current.setNext(null);
        current.setPrevious(null);
        current = nextNode;
        --count;
      } while (current != endNode);
      
      endNode.setNext(null);
      endNode.setPrevious(null);
      --count;

      if (count > 0) {
        previousNode.setNext(head);
        head.setPrevious(previousNode);
        tail = previousNode;
      } else {
        head = null;
        tail = null;
      }
    } catch (Exception e){
      System.out.println(e);
    }

    return head;
  }

  public void removeFrom(int index) {
    if (isEmpty() || head == null)
      throw new NullPointerException("A lista está vazia ou o head é nulo.");

    if (index >= count || index < 0)
      throw new IndexOutOfBoundsException("Linha não encontrada.");

    node current = head;
    int currentIdx = 0;

    while (currentIdx < index) {
      current = current.getNext();
      currentIdx++;
    }

    while (currentIdx >= 0) {
      node previous = current.getPrevious();
      node next = current.getNext();

      // Desconectando o nó atual
      if (previous != null)
        previous.setNext(next);

      if (next != null)
        next.setPrevious(previous);

      if (current == head)
        head = next;

      if (current == tail)
        tail = previous;

      current = previous;
      currentIdx--;
      count--;

      if (count == 0) {
        head = null;
        tail = null;
        break;
      }

    }
  }
	
  public void marcarLinhas(int linhaIni, int linhaFim) {
    node atual = head;
		  
    for(int i = 0; i < linhaIni; i++) {
    	atual.desMarcarNo();
    	atual = atual.getNext();
    }
		  
    node aux = atual;
    for(int j = linhaIni; j <= linhaFim; j++) {
	aux.MarcarNo();
	System.out.println("Nó Marcado: \n" + aux.getMarca());
	aux = aux.getNext();
    }
 }
	  
 public void tranferirNos(LinkedList outra) {
   node atual = head;
   for(int i = 0; i < count(); i++) {
      if(atual.getMarca()){
    	  outra.append(atual.getData());
      }
      atual = atual.getNext();
    }
  }	

  public void insertAfter(int posLin, LinkedList newList) {
    if (posLin < 0 || posLin > count) {
      System.out.println("Posição inválida");
      return;
    }

    //Caso a lista original esteja vazia.
    if (count == 0){
      head = newList.getHead();
      tail = newList.getTail();
      count = newList.count();
      return;
    }

    node current = head;
    for (int i = 0; i < posLin; i++) {
      current = current.getNext();
    }

    node next = current.getNext();

    node newHead = newList.getHead();
    node newTail = newList.getTail();

    current.setNext(newHead);
    newHead.setPrevious(current);

    newTail.setNext(next);
    next.setPrevious(newTail);

    count += newList.count();
  }

  public void insertBefore(int posLin, LinkedList newList) {
    if (posLin < 0 || posLin > count) {
        System.out.println("Posição fora de alcance.");
        return;
    }

    // Caso esteja vazio.
    if (count == 0){
      head = newList.getHead();
      tail = newList.getTail();
      head.setPrevious(tail);
      tail.setNext(head);
      count = newList.count();
      return;
    }

    node current = head;
    for (int i = 0; i < posLin; i++) {
        current = current.getNext();
    }

    node newTail = newList.getTail();
    node newHead = newList.getHead();

    if (current == head) {
      node previous = tail;
      newTail.setNext(head);
      head.setPrevious(previous);
      previous.setNext(newHead);
      head = newHead;
    } else {
        node previous = current.getPrevious();
        previous.setNext(newHead);
        newHead.setPrevious(previous);

        newTail.setNext(current);
        current.setPrevious(newTail);
    }
    count += newList.count();
}
  
  public void clear() {
	  node current = head;
	  for (int i = 0; i < count; ++i) {
		  node next = current.getNext();
		  current.setPrevious(null);
		  current.setNext(null);
      current = next;
	  }
	  head = null;
    tail = null;
    count = 0;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "";
    }

    StringBuilder sb = new StringBuilder();

    node aux = head;
    do {
      sb.append(aux + "\n");
      aux = aux.getNext();
    } while (aux != head);

    return sb.toString();
  }
}
