// Nome: Bruno Antico Galin	| RA: 10417318 
// Nome: Enzo Benedetto Proença | RA: 10418579 
// Nome: Gabriel Alves de Freitas Spinola Sucupira | RA: 10418133
// Nome: Ismael de Sousa e Silva | RA: 10410870 
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=N6dOwBde7-M
// Referência: https://www.youtube.com/watch?v=VJgCjLuU4e8&list=PLqleLpAMfxGDVu5tUmUg9jSQUUB8_5DB0
import java.util.Scanner;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        LinkedList list = new LinkedList();
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
                    System.out.println("Lista: (count = " + list.count() + ") \n" + list);
                    open = path;
                } else {
                    System.out.println("Insira o nome do arquivo ao lado do comando.");
                }
            }
            /*
             * else if (opcao.equals(":w")){
             * if (list.getHead() != null) {
             * String archive = "src/main/java/test.txt";
             * list.save(archive);
             * }
             * else{
             * System.out.println("Não há nada para salvar.");
             * }
             * }
             */

            else if (opcao.startsWith(":w")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length == 2) {
                    String archive = comando[1];
                    File file = new File(archive);
                    String path = file.getAbsolutePath();
                    if (list.getHead() != null) {
                        list.save(archive);
                        saved = true;
                    }
                    else{
                    System.out.println("Não há nada para salvar.");
                    }
                }
                else if (comando.length == 1){
                    if (list.getHead() != null) {
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

            else if (opcao.equals(":q!")) {
                if (saved) {
                    System.out.println("Programa encerrado.");
                    break;
                }
                else {
                    System.out.println("Parece que o arquivo não foi salvo ainda. Deseja salvar as modificações antes de encerrar? (y/n)");
                    String opcaosave = scan.nextLine();
                    if (opcaosave.equals("y")) {
                        list.save(open);
                        saved = true;
                        System.out.println("Programa encerrado.");
                        break;
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
                    System.out.println("Nós removidos a partir do índice " + index + 1 + " até o início.");
                    list.clear();
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

                list.removeAt(index);
                list.clear();

            }

            else if (opcao.startsWith(":a")) {
                String[] comando = opcao.split("\\s+");
                if (comando.length < 2) {
                    System.out.println("Informe a posição após a qual as novas linhas devem ser inseridas.");
                    continue;
                }

                try {
                    int posLin = (Integer.parseInt(comando[1])) -1;
                    if (posLin >= list.count() || posLin < 0) {
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
                    list.clear();
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
                    int posLin = (Integer.parseInt(comando[1]))-1;
                    if (posLin >= list.count() || posLin < 0) {
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
                    list.clear();
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
