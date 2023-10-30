package sistemaCaptura;

public class Maquina {
    private Integer idMaquina;
    private String nome;
    private  String os;
    private Integer emUso;
    private Integer fkInstituicao;
public  Maquina(){}

    public Maquina(Integer idMaquina,String nome, String os, Integer emUso, Integer fkInstituicao) {
    this.idMaquina =idMaquina;
        this.nome = nome;
        this.os = os;
        this.emUso = emUso;
        this.fkInstituicao = fkInstituicao;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getEmUso() {
        return emUso;
    }

    public void setEmUso(Integer emUso) {
        this.emUso = emUso;
    }

    public Integer getFkInstituicao() {
        return fkInstituicao;
    }

    public void setFkInstituicao(Integer fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "idMaquina='" + idMaquina + '\'' +
                "nome='" + nome + '\'' +
                ", os='" + os + '\'' +
                ", emUso=" + emUso +
                ", fkInstituicao=" + fkInstituicao +
                '}';
    }
}
