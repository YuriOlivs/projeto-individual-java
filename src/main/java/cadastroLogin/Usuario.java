package cadastroLogin;

public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;

    private String codInstituicao;

    Usuario(Integer id, String nome, String email, String senha, String codInstituicao) {
        this.idUsuario = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.codInstituicao = codInstituicao;
    }


    public Integer getId() {
        return this.idUsuario;
    }



    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }


    public String getSenha() {
        return this.senha;
    }

    public  String getCodInstituicao(){
        return  this.codInstituicao;
    }

}


