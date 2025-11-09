@Service class JwtService {
  private final Key key; private final long ttl;
  JwtService(@Value("${security.jwt.secret}") String secret, @Value("${security.jwt.ttl-ms:3600000}") long ttl){
    this.key = Keys.hmacShaKeyFor(secret.getBytes()); this.ttl = ttl;
  }
  String issue(String sub, String role){
    long now=System.currentTimeMillis();
    return Jwts.builder().setSubject(sub).claim("role",role)
      .setIssuedAt(new Date(now)).setExpiration(new Date(now+ttl))
      .signWith(key, SignatureAlgorithm.HS256).compact();
  }
}
