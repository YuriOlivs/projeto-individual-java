package sistemaCaptura;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sistemaCaptura.conexao.Conexao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class HistConsmRecurso {
    private Integer idHistorico;
    private LocalDateTime dataHora = LocalDateTime.now();
    private Double consumo;
    private Integer qtd_janelas_abertas;
    private Integer fkMaquina;
    private Integer fkHardware;
    private Integer fkComponente;
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    Looca looca = new Looca();
    Timer timer = new Timer();
    Timer timer02 = new Timer();


    public HistConsmRecurso() {
    }

    public HistConsmRecurso(Integer idHistorico, LocalDateTime dataHora, Double consumo, Integer qtdJanelasAbertas, Integer fkMaquina, Integer fkHardware, Integer fkComponente) {
        this.idHistorico = idHistorico;
        this.dataHora = dataHora;
        this.consumo = consumo;
        this.qtd_janelas_abertas = qtdJanelasAbertas;
        this.fkMaquina = fkMaquina;
        this.fkHardware = fkHardware;
        this.fkComponente = fkComponente;
    }

    public void mostarHistorico(Integer idMaquina) {

        insertHistorico( idMaquina);
        MonitorarSoftware(idMaquina);

    }

    public void insertHistorico(Integer idMaquina) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Integer consumoCpu = (looca.getProcessador().getUso()).intValue();
                Long consumoDisco = (long) (looca.getGrupoDeDiscos().getTamanhoTotal() / 8e+9);
                Integer qtdJanelasAbertas = looca.getGrupoDeJanelas().getTotalJanelas();
                long consumoRam = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                dataHora = LocalDateTime.now();


                // Inserir dados no banco
                con.update("INSERT INTO historico (dataHora, consumo, qtdJanelasAbertas, fkComponente, fkHardware, fkMaquina) VALUES(?, ?, ?, ?, ?, ?)", dataHora, consumoCpu, qtdJanelasAbertas, 1, 1, idMaquina);
                System.out.println("Inserido no Banco de Dados - CPU: " + consumoCpu + "%");

                con.update("INSERT INTO historico (dataHora, consumo, qtdJanelasAbertas, fkComponente, fkHardware, fkMaquina) VALUES(?, ?, ?, ?, ?, ?)", dataHora, consumoRam, qtdJanelasAbertas, 2, 2, idMaquina);
                System.out.println("Inserido no Banco de Dados - RAM: " + consumoRam + " bytes");

                con.update("INSERT INTO historico (dataHora, consumo, qtdJanelasAbertas, fkComponente, fkHardware, fkMaquina) VALUES(?, ?, ?, ?, ?, ?)", dataHora, consumoDisco, qtdJanelasAbertas, 3, 3, idMaquina);
                System.out.println("Inserido no Banco de Dados - Disco: " + consumoDisco + " GB");

                con.update("INSERT INTO historico (dataHora, consumo, qtdJanelasAbertas, fkComponente, fkHardware, fkMaquina) VALUES(?, ?, ?, ?, ?, ?)", dataHora, consumoDisco, qtdJanelasAbertas, 3, 3, idMaquina);
                System.out.println("Inserido no Banco de Dados - Janelas abertas: " + qtdJanelasAbertas + " Janelas abertas");

                // Exibir dados em forma de tabela
                System.out.println("+---------------------+--------------+-----------------+-------------+----------------------+");
                System.out.println("| Data/Hora           | Consumo CPU  | Consumo RAM    | Consumo Disco | Janelas Abertas |");
                System.out.println("+---------------------+--------------+-----------------+-------------+----------------------+");
                System.out.print("| " + dataHora + " | " + consumoCpu + "%        | ");
                if (consumoRam >= 0) {
                    System.out.print(consumoRam + " bytes  | ");
                } else {
                    System.out.print("N/A             | ");
                }
                if (consumoDisco >= 0) {
                    System.out.print(consumoDisco + " GB  | ");
                } else {
                    System.out.print("N/A             | ");
                }
                System.out.println(qtdJanelasAbertas + " janelas abertas |");
                System.out.println("+---------------------+--------------+-----------------+-------------+----------------------+");
            }
        }, 1000, 1000);


    }

    public void MonitorarSoftware(Integer idMaquina) {

        timer02.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ProcessBuilder processBuilder;
                    if (System.getProperty("os.name").toLowerCase().contains("win")) {
                        processBuilder = new ProcessBuilder("tasklist");
                    } else {
                        processBuilder = new ProcessBuilder("ps", "aux");
                    }

                    processBuilder.redirectErrorStream(true);
                    Process process = processBuilder.start();

                    BufferedReader Busca = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String linhaBusca;
                    String processo = "MySQLWorkbench.exe";
                    String motivo = "O motivo foi pelo uso do: ";

                    while ((linhaBusca = Busca.readLine()) != null) {

                        if (linhaBusca.contains(processo)) {
                            dataHora = LocalDateTime.now();
                            motivo += processo;
                            con.update("INSERT INTO strike(dataHora,validade,motivo,fkMaquina) VALUES (?,?,?,?)", dataHora, 1, motivo, idMaquina);
                            System.out.println("levou Strike");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },0,10000);
        // roda a cada 10 segundos

    }

    public void fecharSistema() {
        System.out.println("exit");
        System.exit(0);

    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public Integer getQtd_janelas_abertas() {
        return qtd_janelas_abertas;
    }

    public void setQtd_janelas_abertas(Integer qtd_janelas_abertas) {
        this.qtd_janelas_abertas = qtd_janelas_abertas;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public Integer getFkHardware() {
        return fkHardware;
    }

    public void setFkHardware(Integer fkHardware) {
        this.fkHardware = fkHardware;
    }

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public JdbcTemplate getCon() {
        return con;
    }

    public void setCon(JdbcTemplate con) {
        this.con = con;
    }

    public Looca getLooca() {
        return looca;
    }

    public void setLooca(Looca looca) {
        this.looca = looca;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer02() {
        return timer02;
    }

    public void setTimer02(Timer timer02) {
        this.timer02 = timer02;
    }

    @Override
    public String toString() {
        return "HistConsmRecurso{" +
                "idHistorico=" + idHistorico +
                ", dataHora=" + dataHora +
                ", consumo=" + consumo +
                ", qtd_janelas_abertas=" + qtd_janelas_abertas +
                ", fkMaquina=" + fkMaquina +
                ", fkHardware=" + fkHardware +
                ", fkComponente=" + fkComponente +
                '}';
    }
}

