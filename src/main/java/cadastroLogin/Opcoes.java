package cadastroLogin;

import java.util.ArrayList;
import java.util.Scanner;


public class Opcoes {

    private static String COR_PADRAO = "\u001B[0m";
    private static String COR_CORRETO = "\u001B[32m";
    private static String COR_LISTA = "\u001B[35m";

    Scanner leitorInformacao = new Scanner(System.in);
    Acessorios acessorios = new Acessorios();
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();


    void opcoesSistema(Integer numOpcao) {

        switch (numOpcao) {
            case 1 -> {

                Boolean resposta = cadastrar();
                if (resposta) {
                    System.out.println(COR_CORRETO + "Você foi cadastrado com suscesso!!" + COR_PADRAO);

                } else {
                    System.err.println( "Houve um erro dentro do nosso sistema");

                }
            }
            case 2 -> {

                Boolean resposta = login();

                if (resposta) {
                    System.out.println(COR_CORRETO + "Você foi logado com suscesso!!" + COR_PADRAO);
                } else {
                    System.err.println( "e-mail não localizado" );
                }
            }
            case 3 -> exibirListaUsuario();
            default -> System.out.println("Opção invalida!!");
        }
    }


    boolean cadastrar() {


        String email;
        String senha;
        String confSenha;
        String nome;
        String codInstituicao;
        Integer idArtificial = listaUsuarios.size() + 1;

        System.out.println("Digite o seu nome de usuario");
        nome = leitorInformacao.nextLine();

        System.out.println("Digite o codigo da instituição que você trabanha");
        codInstituicao = leitorInformacao.nextLine();

        do {

            System.out.println("Digite um e-mail");
            email = leitorInformacao.nextLine();

            if (!email.contains("@")|| !email.contains(".com")) {
                System.err.println("Houve um erro!!!\n" + "Digite um e-mail valido");
            }


        } while (!email.contains("@") || !email.contains("."));




        do {

            System.out.println("Digite a sua senha");
            senha = leitorInformacao.nextLine();

            System.out.println("Confirme a sua senha");
            confSenha = leitorInformacao.nextLine();

            if (!senha.equals(confSenha)) {
                System.err.println("Senhas deferentes!! " + "\nTente novamente.");
            }


        } while (!senha.equals(confSenha));

        listaUsuarios.add(new Usuario (idArtificial, nome, email, senha,codInstituicao));

        return true;

    }

    Boolean login() {
        System.out.println("Digite um e-mail para começar o login");
        String email = leitorInformacao.nextLine();

        System.out.println("Digite sua senha");
        String senha = leitorInformacao.nextLine();

        for (Usuario info : listaUsuarios) {

            if (email.equals(info.getEmail()) && senha.equals(info.getSenha())) {

                System.out.println(COR_CORRETO + "Usuario logado!!\n" +
                        "Dados do usuario logado!" + COR_PADRAO);


                System.out.printf(COR_LISTA + """
                     %s - %s || %s || %s || %s
                    """ + COR_PADRAO, info.getId(), info.getNome(), info.getEmail(), info.getSenha(), info.getCodInstituicao());

                return true;
            }
        }
        return false;
    }


    void exibirListaUsuario() {

       if (listaUsuarios.size()>0){
           System.out.println("Usuarios cadastrados");
           acessorios.linhaDecorada();
           System.out.println(" id - nome || e-mail || senha || Instituição");
           acessorios.linhaDecorada();
           for (Usuario info : listaUsuarios) {

               System.out.printf(COR_LISTA + """
                     %s - %s || %s || %s || %s
                    """ + COR_PADRAO, info.getId(), info.getNome(), info.getEmail(), info.getSenha(), info.getCodInstituicao());
           }
           acessorios.linhaDecorada();

       }else System.err.println("Lista sem registros");

    }


}
