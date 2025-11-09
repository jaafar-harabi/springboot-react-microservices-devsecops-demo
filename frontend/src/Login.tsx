export default function Login(){
  const [u,setU]=useState(''); const [p,setP]=useState(''); const [err,setErr]=useState('');
  async function submit(e:React.FormEvent){ e.preventDefault(); setErr('');
    const r = await fetch('/auth/login',{method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({username:u,password:p})});
    if(!r.ok){ setErr('Bad credentials'); return; }
    const { token } = await r.json(); localStorage.setItem('token', token); window.location.href = '/';
  }
  return;
}
