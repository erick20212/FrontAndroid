package features.auth.presentation.data;

public class LoginRequest {
    public String email;
    public String password;
    public LoginRequest(String e, String p){ email = e; password = p; }
}