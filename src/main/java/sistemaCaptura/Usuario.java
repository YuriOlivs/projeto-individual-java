package sistemaCaptura;

public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;

   private Integer fkInstituicao;
    private Integer idInstituicao;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }


    public Usuario(Integer idInstituicao, Integer fkInstituicao, String nome, Integer idUsuario) {
        this.idInstituicao = idInstituicao;
        this.fkInstituicao = fkInstituicao;
        this.nome = nome;
        this.idUsuario =idUsuario;
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFkInstituicao() {
        return fkInstituicao;
    }

    public void setFkInstituicao(Integer fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idInstituicao='" + idInstituicao + '\'' +
                ", fkInstituicao='" + fkInstituicao + '\'' +
                ", nome='" + nome + '\'' +
                ",idUsuario=" + idUsuario +
                '}';
    }
}
