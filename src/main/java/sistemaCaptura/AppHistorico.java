package sistemaCaptura;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sistemaCaptura.conexao.Conexao;

import java.util.List;
import java.util.Scanner;

public class AppHistorico {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        HistConsmRecurso histConsmRecurso = new HistConsmRecurso();

        Scanner in = new Scanner(System.in);
        Scanner leitor = new Scanner(System.in);
        Integer escolha;
        do {
            System.out.println("*"+"-".repeat(40)+"*");
            System.out.println("Bem vindo!");
            System.out.println("Você está utilizando o nosso sistema de monitoramento de hardware, o Magister!");
            System.out.println("\nEscolha uma das opções abaixo");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Sair");
            System.out.println("*"+"-".repeat(40)+"*");

            escolha = in.nextInt();
            switch (escolha) {
                case 1 -> {
                    System.out.println("Digite o seu email");
                    String email = leitor.nextLine();

                    System.out.println("Digite o sua senha");
                    String senha = leitor.nextLine();

                    List<Usuario> usuario = con.query("""  
                            select
                            i.idInstituicao,
                            u.fkInstituicao,
                            u.nome,
                            u.idUsuario
                             from instituicao i JOIN usuario u where  email=? AND senha =?;
                            """, new BeanPropertyRowMapper<>(Usuario.class), email, senha);
// will.adolpho@sptech.school SelokoPai69# 000000

                    if (usuario.size() > 0) {
                        System.out.println("Bem vindo " + usuario.get(0).getNome());

                        Integer opcaoUsuario;
                        do {
                            System.out.println("*"+"-".repeat(40)+"*");
                            System.out.println("Escolha uma das opçções abaixo para prosseguir:");
                            System.out.println("1 - Ativar maquina");
                            System.out.println("2 - Análise das máquinas");
                            System.out.println("3 - Fechar sistema");
                            System.out.println("*"+"-".repeat(40)+"*");

                            opcaoUsuario = in.nextInt();
                            switch (opcaoUsuario) {
                                case 1:
                                    List<Maquina> maquinas = con.query("""  
                                            select * from maquina where fkInstituicao=? AND emUso =0;
                                            """, new BeanPropertyRowMapper<>(Maquina.class), usuario.get(0).getIdInstituicao());
                                    System.out.println("-".repeat(15));

                                    System.out.println("Escolha uma maquina disponivel");
                                    for (int i = 0; i < maquinas.size(); i++) {
                                        System.out.println(String.format("""
                                                id: %d - nome: %s
                                                  """, maquinas.get(i).getIdMaquina(), maquinas.get(i).getNome()));
                                    }
                                    System.out.println("-".repeat(15));
                                    Integer numMaquina = in.nextInt();
                                    histConsmRecurso.mostarHistorico(numMaquina);

                                    break;
                                case 2:
                                    String consulta = """
                                            SELECT\s
                                            	m.idMaquina as id,
                                            	m.nome as nome,
                                                m.so as so,
                                                m.emUso as emUso,
                                            	(SELECT concat(fabricante, ' ', modelo, ' ', especificidade) FROM hardware JOIN componente ON fkHardware = idHardware JOIN maquina ON fkMaquina = idMaquina WHERE fkTipoHardware = 3 AND idMaquina = 1)\s
                                                as componenteRAM,
                                                (SELECT capacidade FROM hardware JOIN componente ON fkHardware = idHardware JOIN maquina ON fkMaquina = idMaquina WHERE fkTipoHardware = 3 AND idMaquina = 1)\s
                                                as capacidadeRAM,
                                            	(SELECT concat(fabricante, ' ', modelo, ' ', especificidade) FROM hardware JOIN componente ON fkHardware = idHardware JOIN maquina ON fkMaquina = idMaquina WHERE fkTipoHardware = 2 AND idMaquina = 1)\s
                                                as componenteCPU,
                                                (SELECT capacidade FROM hardware JOIN componente ON fkHardware = idHardware JOIN maquina ON fkMaquina = idMaquina WHERE fkTipoHardware = 2 AND idMaquina = 1)\s
                                                as capacidadeCPU,
                                                (SELECT concat(fabricante, ' ', modelo, ' ', especificidade) FROM hardware JOIN componente ON fkHardware = idHardware JOIN maquina ON fkMaquina = idMaquina WHERE fkTipoHardware = 1 AND idMaquina = 1)\s
                                                as componenteDisco,
                                                (SELECT capacidade FROM hardware JOIN componente ON fkHardware = idHardware JOIN maquina ON fkMaquina = idMaquina WHERE fkTipoHardware = 1 AND idMaquina = 1)\s
                                                as capacidadeDisco,
                                                (SELECT COUNT(*) FROM strike JOIN maquina ON fkMaquina = idMaquina WHERE fkMaquina = 1) as qtdStrikes
                                            FROM maquina m
                                            JOIN componente c ON c.fkMaquina = m.idMaquina
                                            JOIN hardware ram ON c.fkHardware = ram.idHardware
                                            JOIN hardware cpu ON c.fkHardware = cpu.idHardware
                                            JOIN hardware disco ON c.fkHardware = disco.idHardware;
                                            """;
                                    List<Registro> registros = con.query(consulta,
                                            new BeanPropertyRowMapper<>(Registro.class));

                                    for (Registro r:registros) {
                                        r.mostrarRelatorio();
                                    }
                                    break;
                                case 3:
                                    histConsmRecurso.fecharSistema();
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        } while (opcaoUsuario != 2);

                    } else {
                        System.out.println("Dados da intituição ou de usuario invalidos");
                    }


                }
                case 2 -> histConsmRecurso.fecharSistema();

                default -> System.out.println("Opção invalida ");

            }

        } while (escolha != 2);


    }


}
