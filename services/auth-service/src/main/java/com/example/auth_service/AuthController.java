record RegisterRequest(String username,String password){}
record LoginRequest(String username,String password){}
@RestController @RequestMapping("/auth")
class AuthController {
  private final UserAccountRepository repo; private final PasswordEncoder enc; private final JwtService jwt;
  AuthController(UserAccountRepository r, PasswordEncoder e, JwtService j){ repo=r; enc=e; jwt=j; }
  @PostMapping("/register") ResponseEntity<?> register(@RequestBody RegisterRequest req){
    if(repo.findByUsername(req.username()).isPresent()) return ResponseEntity.badRequest().body(Map.of("error","exists"));
    var u=new UserAccount(); u.setUsername(req.username()); u.setPasswordHash(enc.encode(req.password())); repo.save(u);
    return ResponseEntity.ok(Map.of("status","ok"));
  }
  @PostMapping("/login") ResponseEntity<?> login(@RequestBody LoginRequest req){
    var u=repo.findByUsername(req.username()).orElse(null);
    if(u==null || !enc.matches(req.password(),u.getPasswordHash())) return ResponseEntity.status(401).body(Map.of("error","bad_credentials"));
    return ResponseEntity.ok(Map.of("token", jwt.issue(u.getUsername(), u.getRole())));
  }
}
