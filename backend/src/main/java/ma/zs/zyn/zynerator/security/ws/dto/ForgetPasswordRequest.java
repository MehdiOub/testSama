package ma.zs.zyn.zynerator.security.ws.dto;

public class ForgetPasswordRequest {
    private String email  ;
    private String password  ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
