package sistemaCaptura;

public class Registro {
    private Integer id;
    private String nome;
    private String so;
    private String ram;
    private Integer capacidadeRam;
    private String cpu;
    private Integer capacidadeCpu;
    private String disco;
    private Integer capacidadeDisco;

    public Registro() {}
    //funções próprias


    public Registro(Integer id, String nome, String so, String ram, Integer capacidadeRam, String cpu, Integer capacidadeCpu, String disco, Integer capacidadeDisco) {
        this.id = id;
        this.nome = nome;
        this.so = so;
        this.ram = ram;
        this.capacidadeRam = capacidadeRam;
        this.cpu = cpu;
        this.capacidadeCpu = capacidadeCpu;
        this.disco = disco;
        this.capacidadeDisco = capacidadeDisco;
    }

    public void mostrarRelatorio() {
        System.out.println("""
                \n*-* RELATÓRIO DA MÁQUINA %s *-*
                | ID: %d
                | Nome: %s
                | Sistema Operacional: %s
                | Modelo da RAM: %s
                | Capacidade da RAM: %d GB
                | Modelo da CPU: %s
                | Capacidade da CPU: %d Ghz
                | Modelo do Disco: %s
                | Capacidade do Disco: %d GB
                """.formatted(nome, id, nome, so, ram, capacidadeRam, cpu, capacidadeCpu, disco, capacidadeDisco));
    }

    public Integer getIdMaquina() {
        return id;
    }

    public void setIdMaquina(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Integer getCapacidadeRam() {
        return capacidadeRam;
    }

    public void setCapacidadeRam(Integer capacidadeRam) {
        this.capacidadeRam = capacidadeRam;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Integer getCapacidadeCpu() {
        return capacidadeCpu;
    }

    public void setCapacidadeCpu(Integer capacidadeCpu) {
        this.capacidadeCpu = capacidadeCpu;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public Integer getCapacidadeDisco() {
        return capacidadeDisco;
    }

    public void setCapacidadeDisco(Integer capacidadeDisco) {
        this.capacidadeDisco = capacidadeDisco;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "idMaquina=" + id +
                ", nome='" + nome + '\'' +
                ", so='" + so + '\'' +
                ", ram='" + ram + '\'' +
                ", capacidadeRam=" + capacidadeRam +
                ", cpu='" + cpu + '\'' +
                ", capacidadeCpu=" + capacidadeCpu +
                ", disco='" + disco + '\'' +
                ", capacidadeDisco=" + capacidadeDisco +
                '}';
    }
}
