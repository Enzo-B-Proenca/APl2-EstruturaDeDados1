// Nome: Bruno Antico Galin	| RA: 10417318 
// Nome: Enzo Benedetto Proença | RA: 10418579 
// Nome: Gabriel Alves de Freitas Spinola Sucupira | RA: 10418133
// Nome: Ismael de Sousa e Silva | RA: 10410870 
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=N6dOwBde7-M
// Referência: https://www.youtube.com/watch?v=VJgCjLuU4e8&list=PLqleLpAMfxGDVu5tUmUg9jSQUUB8_5DB0

// IMPORTANTE! Quando for testar, coloque os arquivos .java dentro do src e os arquivos .txt fora do src, senão para abrir os txt tem que pegar seu caminho inteiro
import java.util.Scanner;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        LinkedList list = new LinkedList();
        LinkedList areaTransferencia = new LinkedList();
        boolean selecao = false;
        boolean saved = false;
        String open = null;
        do {
            System.out.println("\n***EDITOR DE TEXTO***\nDigite uma opção: ");
            String opcao = scan.nextLine();
            if (opcao.startsWith(":e")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length == 2) {
                    String archive = comando[1];
                    File file = new File(archive);
                    String path = file.getAbsolutePath();
                    list.read(path);
                    open = path;
                } else {
                    System.out.println("Insira o nome do arquivo ao lado do comando.");
                }
            }

             else if (opcao.startsWith(":w")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length == 2) {
                    String archive = comando[1];
                    File file = new File(archive);
                    String path = file.getAbsolutePath();
                    if (!list.isEmpty()) {
                        list.save(archive);
                        saved = true;
                    }
                    else{
                    System.out.println("Não há nada para salvar.");
                    }
                }
                else if (comando.length == 1){
                    if (!list.isEmpty()) {
                        if (open != null) {
                        list.save(open);
                        saved = true;
                        }
                    }
                    else{
                        System.out.println("Não há nada para salvar.");
                    }
                }
            }
                 
            else if(opcao.startsWith(":v")) {
            	String[] comando = opcao.split("\\\s");
    			if(comando.length < 3) {
    				System.err.println("Erro: Digite dois numeros inteiros");
    				continue;
    			}
            	if(comando.length == 3) {
            		String archive1 = comando[1]; String archive2 = comando[2];
            		
            		try {
            			int LinhaIni = Integer.parseInt(archive1)-1;
                		int LinhaFim = Integer.parseInt(archive2)-1;
            			if(LinhaIni > LinhaFim) {
            				System.err.println("Erro! Intervalo de números inválido!");
            				continue;
            			}

                		if(LinhaIni <= list.count() && LinhaFim <= list.count()) {
                			list.marcarLinhas(LinhaIni, LinhaFim);
                			selecao = true;
                			continue;
                		} 
                		else {
                			System.err.println("Erro: Intervalo fora de alcance!");
                			continue;
                		}
            		} catch(NumberFormatException e) {
            			System.err.println("Erro: A entrada deve ser um numero inteiro.");
            			continue;
            		}
            	}
            	System.err.println("Erro: Excesso de argumentos");
            }
            
            else if(opcao.equals(":y")) {
            	if(!selecao) {
            		System.err.println("Erro: Selecione algo antes de copiar");
            		continue;
            	}
            	areaTransferencia.clear();
            	list.tranferirNos(areaTransferencia);
            	System.out.println("Linhas copiadas: " + areaTransferencia.count());
            }
            
            else if(opcao.startsWith(":c")) {
            	String[] comando = opcao.split("\\s+");
            	if(comando.length != 1) {
            		System.err.println("Erro: Função não requer argumentos!");
            		continue;
            	}
            	
            	if (!selecao) {
            		System.err.println("Erro: Selecione algo antes de recortar!");
            		continue;
            	}
            	
            	// Colocar o selecionado na area de transferencia
            	areaTransferencia.clear();
            	list.tranferirNos(areaTransferencia);
            	
            	// Apagar da lista original o que foi recortado
            	node percorre = list.getHead();
            	node next = percorre.getNext();
            	
            	for(int i = 0 ; i < list.count() ; i++) {
            		// Se o node foi marcado para a area de transferencia remover ele
            		if (percorre.getMarca()) {
            			list.removeAt(i);
            		}
            		percorre = next;
            		next = percorre.getNext();
            	}
            	
            	// como recortou nao ha mais nada selecionado
            	selecao = false;
            	
            	System.out.println("Linhas removidas e enviadas para a area de transferencia com sucesso.");
            }
            
            else if (opcao.startsWith(":p")) {
            	
            	if(areaTransferencia.getHead() == null) {
            		System.err.println("Erro: Selecione algo antes de colar!");
            		continue;
            	}
            	
            	String[] comando = opcao.split("\\s+");
            	if (comando.length < 2) {
            		System.err.println("Erro: Insira uma linha para inserir o que foi copiado!");
            		continue;
            	}
            	else if(comando.length > 2) {
            		System.err.println("Erro: Função requer apenas 1 argumento!");
            		continue;
            	}
            	try {
            		int linha = Integer.parseInt(comando[1]) - 1;
            		
            		if (linha < 0 || linha > list.count()) {
            			System.err.println("Erro: linha nao encontrada");
            		}
            		            		
            		node nodeAux2 = areaTransferencia.getHead();
            		LinkedList Aux = new LinkedList();
            		
            		for (int i = 0; i < areaTransferencia.count(); i++) {
            			Aux.append(nodeAux2.getData());
            			nodeAux2 = nodeAux2.getNext();
            		}
            		list.insertAfter(linha, Aux);
            		System.out.println("Linhas inseridas com sucesso!");
            	}catch(NumberFormatException e) {
            		System.err.println("Erro: O parametro deve ser um numero!");
            	}
            	
            }
            
            
            else if (opcao.equals(":s")) {
                String auxiliar = "";
                int contador_linhas = 1;
                node percorre = list.getHead();
                String[] comando = opcao.split("\s+");
                if (comando.length == 1) {
                    do {
                        auxiliar = (contador_linhas + " " + percorre.getData());
                        System.out.println(auxiliar);
                        if(contador_linhas % 20 == 0) {
                            System.out.println("\n");
                            System.out.println("=========================================");
                        }
                        contador_linhas++;
                        percorre = percorre.getNext();
                    }while(percorre != list.getHead());
                }
                else {
                    if(comando[1] == "1") {}
                }

            }
            
            else if (opcao.startsWith(":s")) {
                String auxiliar = "";
                int contador_linhas = 1;
                node percorre = list.getHead();
                int LinhaInicio = 1;
                int LinhaFimm = list.count();
                String[] comando = opcao.split("\s+");
                
                if(comando.length < 3) {
                	System.out.println("Erro: Digite dois numeros inteiros.");
                }

                if (comando.length == 3) {
                    String num1 = comando[1]; String num2 = comando[2];
                    
                    try {
                    	LinhaInicio = Integer.parseInt(num1); 
                    	LinhaFimm = Integer.parseInt(num2);
                    	
                    	if(LinhaInicio > LinhaFimm) {
            				System.out.println("Erro! Intervalo de números inválido!");
            			}
                    	
                    	for(int i = 1; i < LinhaInicio; i++) {
                        	contador_linhas++;
                            percorre = percorre.getNext();
                        }

                        node aux = percorre;
                        for(int j = LinhaInicio; j <= LinhaFimm; j++) {
                            auxiliar = (contador_linhas + " " + aux.getData());
                            System.out.println(auxiliar);
                            contador_linhas++;
                            if(contador_linhas % 20 == 1) {
                                System.out.println("\n");
                                System.out.println("=========================================");
                            }
                            aux = aux.getNext();
                        }
                    } catch(NumberFormatException e) {
                    	System.err.println("Erro: A entrada deve ser um numro inteiro.");
                    }
                }
            }                 

            else if (opcao.equals(":q!")) {
                if (saved) {
                    System.out.println("Programa encerrado.");
                    break;
                }
                else {
                    System.out.println("Parece que o arquivo não foi salvo ainda. Deseja salvar as modificações antes de encerrar? (y/n)");
                    String opcaosave = scan.nextLine();
                    if (opcaosave.equals("y")) {
                    	try {
                    		list.save(open);
                    		saved = true;
                    		System.out.println("Programa encerrado.");
                    		break;
                    	}
                    	catch (NullPointerException e) {
                    		System.out.println("O arquivo não foi aberto ainda. Tente novamente.");
                    	}
                    }
                    else if (opcaosave.equals("n")) {
                        System.out.println("Programa encerrado.");
                        break;
                    }
                    else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }

            else if (opcao.startsWith(":xG")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length < 2) {
                    System.out.println("Informe o número da linha até onde deve ser removida.");
                    continue;
                }

                try {
                    int index = (Integer.parseInt(comando[1]));

                    if (index > list.count() || index < 0) {
                        System.out.println("Operação inválida, linha fora de alcance.");
                        continue;
                    }

                    if (list.isEmpty() || list.getHead() == null) {
                        System.out.println("A lista está vazia ou o head é nulo.");
                        continue;
                    }

                    list.removeUntil(index);
                    System.out.println("Nós removidos a partir do indice: " + (index + 1) + " até o final da lista.");

                } catch (NumberFormatException e) {
                    System.out.println("Erro: o índice fornecido não é um número válido.");
                }
            }

            else if (opcao.startsWith(":XG")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length < 2) {
                    System.out.println("Informe o número da linha a partir de onde deve ser removida até o início.");
                    continue;
                }

                try {
                    int index = (Integer.parseInt(comando[1])) - 1;
                    System.out.println("Index = " + index);

                    if (index > list.count() || index < 0) {
                        System.out.println("Operação inválida, linha fora de alcance.");
                        continue;
                    }
                    if (list.isEmpty() || list.getHead() == null) {
                        System.out.println("A lista esta vaziai ou o head é nulo.");
                        continue;
                    }

                    list.removeFrom(index);
                    System.out.println("Nós removidos a partir do índice " + (index + 1) + " até o início.");
                } catch (NumberFormatException e) {
                    System.out.println("Erro: o índice fornecido não é um número válido.");
                }
            }

            else if (opcao.startsWith(":x")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length < 2)
                    System.out.println("Informe o número da linha a ser removida.");

                int index = Integer.parseInt(comando[1]);
                if (index > list.count() || index < 0)
                    System.out.println("Operação inválida, linha fora de alcance.");

                list.removeAt(index-1);

            }

            else if (opcao.startsWith(":/")) {
            	String[] comando = opcao.split("\\s+");
            	// StringBuilder para quando precisar alterar alguma Data de um node
            	StringBuilder alterar = new StringBuilder();
            	
            	boolean trocou = false;
            	// Se nao houver o que ser procurado mostrar o erro
            	if (comando.length == 1) {
            		System.err.println("Erro: Operacao invalida, faltam argumentos!");
            		continue;
            	}
            	// Se houverem 5 ou mais argumentos mostra o erro
            	else if(comando.length > 4) {
            		System.err.println("Erro: Operacao invalida, há mais que 4 argumentos!");
            		continue;
            	}
            	
            	node percorre = list.getHead();
            	int linha_atual = 1;
            	
            	if (comando.length == 2) {
            		String[] procurar = percorre.getData().split("\\s+");
            		do {
            			// Loop para percorrer as plavras da Data do node atual e comparar com o procurado
            			for (int i = 0 ; i < procurar.length ; i++) {
            				if (comando[1].equals(procurar[i])) {
            					System.out.println(linha_atual + " " + percorre.getData());
            					trocou = true;
            				}

            			}
            			// Atualizar para a proxima linha
            			linha_atual++;
            			percorre = percorre.getNext();
            			procurar = percorre.getData().split("\\s+");
            		}while(percorre != list.getHead());
            		if (!trocou){
            			System.out.println("Palavra nao encontrada!");
            		}
            		continue;
            	
            	}
            	
            	else if(comando.length == 3) {
            		String[] procurar = percorre.getData().split("\\s+");
            		
            		do {
            			// Loop para percorrer as plavras da Data do node atual e comparar com o procurado
            			for (int i = 0 ; i < procurar.length ; i++) {
            				if (comando[1].equals(procurar[i])) {
            					procurar[i] = comando[2];
            					trocou = true;
            				}
            				// entra palavra por palavra no stringbuilder
            				alterar.append(procurar[i]);
            				alterar.append(" ");
            			}
            			
            			// se a Data foi alterada altera
            			percorre.setData(alterar.toString());
            			// Reseta o string builder
            			alterar.setLength(0);
            			// Atualizar para a proxima linha
            			linha_atual++;
            			percorre = percorre.getNext();
            			procurar = percorre.getData().split("\\s+");
            			
            		} while(percorre != list.getHead());
            		if(trocou) {
                		System.out.println("Troca efetuada com sucesso!");
                		continue;
                	}
                	
                	System.out.println("Palavra nao encontrada, sem alteracoes!");
            		continue;
            	}
            	
            	try {
            		int linha = Integer.parseInt(comando[3]);
            		
            		System.out.println(linha);
                	
                	if (linha < 0 | linha > list.count()) {
                		System.err.println("Erro: Posicao invalida, insira uma linha que exista no texto!");
                	}
                	
                	while (linha_atual < linha) {
                		linha_atual++;
                		percorre = percorre.getNext();
                	}
                	
                	String[] palavras = percorre.getData().split("\\s+");
                	
                	
                	for (int i = 0 ; i < palavras.length ; i++) {
                		// quando encontrar a palavra a ser substituida troca ela
                		if (comando[1].equals(palavras[i])) {
                			palavras[i] = comando[2];
                			trocou = true;
                		}
                		alterar.append(palavras[i]);
                		alterar.append(" ");
                		
                		percorre.setData(alterar.toString());
                		
                	}
                	// Resetar o StringBuilder
                	alterar.setLength(0);
                	if(trocou) {
                		System.out.println("Troca efetuada com sucesso!");
                		continue;
                	}
                	
                	System.out.println("Palavra nao encontrada, sem alteracoes!");
                	
                
            	} catch (NumberFormatException e) {
            		System.err.println("Erro: Entrada de linha inválida, insira números inteiros!");
            		continue;
            	}
            }

            else if (opcao.startsWith(":a")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length < 2) {
                    System.out.println("Informe a posição após a qual as novas linhas devem ser inseridas.");
                    continue;
                }

                try {
                    int posLin = (Integer.parseInt(comando[1])) -1;
                    if (posLin > list.count() || posLin < 0) {
                        System.out.println("Operação inválida, posição fora de alcance.");
                        continue;
                    }
                    System.out.println("Insira as novas linhas (termine com :a em uma linha vazia):");
                    Scanner a = new Scanner(System.in);
                    LinkedList newList = new LinkedList();
                    String line;
                    while (!(line = a.nextLine()).equals(":a")) {
                        newList.append(line);
                    }
                    list.insertAfter(posLin, newList);
                    System.out.println("Novas linhas inseridas.");
                } catch (NumberFormatException e) {
                    System.out.println("Erro: a posição fornecida não é um número válido.");
                }
            }

            else if (opcao.startsWith(":i")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length < 2) {
                    System.out.println("Informe a posição antes da qual as novas linhas devem ser inseridas.");
                    continue;
                }

                try {
                    int posLin = Integer.parseInt(comando[1])-1;
                    if (posLin > list.count() || posLin < 0) {
                        System.out.println("Operação inválida, posição fora de alcance.");
                        continue;
                    }
                    System.out.println("Insira as novas linhas (termine com :i em uma linha vazia):");

                    LinkedList newList = new LinkedList();
                    String line;
                    while (!(line = scan.nextLine()).equals(":i")) {
                        newList.append(line);
                    }

                    list.insertBefore(posLin, newList);
                    System.out.println("Novas linhas inseridas.");
                    saved = false;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: a posição fornecida não é um número válido.");
                }
            }

            else if (opcao.equals(":help")) {
                System.out.println(
                        "***EDITOR DE TEXTO***\n:e NomeArq.ext | Abrir o arquivo de nome NomeArq.ext e armazenar cada linha em um nó da lista.\n:w | Salvar a lista no arquivo atualmente aberto.\n:w NomeArq.ext | Salvar a lista no arquivo de nome NomeArq.ext.\n:q! | Encerrar o editor. Caso existam modificações não salvas na lista, o programa deve solicitar confirmação se a pessoa usuária do editor deseja salvar as alterações em arquivo antes de encerrar o editor.\n:v LinIni LinFim | Marcar um texto da lista (para cópia ou recorte – “área de transferência”) da LinIni até LinFim. Deve ser verificado se o intervalo [LinIni, LinFim] é válido.\n:y | Copiar o texto marcado (ver comando anterior) para uma lista usada como área de transferência.\n:c | Recortar o texto marcado para a lista de área de transferência.\n:p LinIniColar |  Colar o conteúdo da área de transferência na lista, a partir da linha indicada em LinIniColar. Deve ser verificado se LinIniColar é válido.\n:s | Exibir em tela o conteúdo completo do código-fonte que consta na lista, de 20 em 20 linhas.\n:s LinIni LinFim | Exibir na tela o conteúdo do código-fonte que consta na lista, da linha inicial LinIni até a linha final LinFim, de 20 em 20 linhas.\r\n:x Lin | Apagar a linha de posição Lin da lista.\n:xG Lin | Apagar o conteúdo a partir da linha Lin até o final da lista.\n:XG Lin | Apagar o conteúdo da linha Lin até o início da lista.\n:/ Elemento | Percorrer a lista, localizar as linhas que contém Elemento e exibir o conteúdo das linhas por completo.\n:/ Elem ElemTroca | Percorrer a lista e realizar a troca de Elem por ElemTroca em todas as linhas do código-fonte.\n:/ Elem ElemTroca Linha |  Realizar a troca de Elem por ElemTroca na linha Linha do códigofonte.\n:a PosLin | Permitir a inserção de uma ou mais linhas e inserir na lista depois da posição PosLin. O término da entrada do novo conteúdo é dado por um :a em uma linha vazia.\n:i PosLin | Permitir a inserção de uma ou mais linhas e inserir na lista antes da posição PosLin. O término da entrada do novo conteúdo é dado por um :i em uma linha vazia.\n:help | Apresentar na tela todas as operações permitidas no editor.\n");
            }

            else {
                System.out.println("Opção inválida. Tente novamente");
            }
        } while (true);
        scan.close();
    }

}
