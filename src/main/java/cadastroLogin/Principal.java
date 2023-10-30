package cadastroLogin;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner leitorOp = new Scanner(System.in);
        Opcoes servico = new Opcoes();

        Integer opcao;
        do {

            String texto = """
                    Escolha a sua opção de entrada
                    ==------------------------------==
                     1 - Cadastro
                     2 - Login
                     3 - Mostrar lista 
                     4 - Finalizar o sistema
                    ==------------------------------==
                                    
                    """;
            System.out.println(texto);

            opcao = leitorOp.nextInt();

            servico.opcoesSistema(opcao);


        } while (!opcao.equals(4));


        System.out.println("fim da operação");


        leitorOp.close();
    }
}
