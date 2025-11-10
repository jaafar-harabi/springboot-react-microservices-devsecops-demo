import { useState } from 'react';
import { api } from './api';

export default function Login() {
  const [u, setU] = useState(''); const [p, setP] = useState(''); const [err, setErr] = useState('');
  async function submit(e: React.FormEvent) {
    e.preventDefault(); setErr('');
    try {
      const { token } = await api.login(u, p);
      localStorage.setItem('token', token);
      location.href = '/';
    } catch {
      setErr('Invalid credentials');
    }
  }
  return (
    <div style={{ maxWidth: 360, margin: '80px auto', fontFamily: 'sans-serif' }}>
      <h2>Sign in</h2>
      <form onSubmit={submit}>
        <input placeholder="username" value={u} onChange={e=>setU(e.target.value)} />
        <input placeholder="password" type="password" value={p} onChange={e=>setP(e.target.value)} />
        <button>Login</button>
      </form>
      {err && <p style={{color:'crimson'}}>{err}</p>}
    </div>
  );
}
