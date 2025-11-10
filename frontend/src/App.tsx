import { useEffect, useState } from 'react';
import { api } from './api';

type Task = { id: number; title: string; done: boolean; createdAt: string };

export default function App() {
  const [items, setItems] = useState<Task[]>([]);
  const [title, setTitle] = useState('');
  async function load() { setItems(await api.tasksList()); }
  useEffect(()=>{ load(); }, []);
  async function add() { if(!title.trim()) return; await api.tasksCreate(title.trim()); setTitle(''); await load(); }
  async function toggle(t: Task){ await api.tasksUpdate(t.id, t.title, !t.done); await load(); }
  async function del(id: number){ await api.tasksDelete(id); await load(); }
  function logout(){ localStorage.removeItem('token'); location.href='/login'; }

  return (
    <div style={{ maxWidth: 680, margin: '40px auto', fontFamily: 'sans-serif' }}>
      <h2>Tasks</h2>
      <div>
        <input placeholder="new task" value={title} onChange={e=>setTitle(e.target.value)} />
        <button onClick={add}>Add</button>
        <button onClick={logout} style={{float:'right'}}>Logout</button>
      </div>
      <ul>
        {items.map(t=>(
          <li key={t.id}>
            <label>
              <input type="checkbox" checked={t.done} onChange={()=>toggle(t)} />
              {t.title}
            </label>
            <button onClick={()=>del(t.id)} style={{marginLeft: 8}}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
