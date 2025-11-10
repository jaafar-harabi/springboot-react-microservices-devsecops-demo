export const API = import.meta.env.VITE_API_BASE_URL ?? '';

async function request(path: string, options: RequestInit = {}) {
  const token = localStorage.getItem('token');
  const headers = new Headers(options.headers);
  headers.set('Content-Type', 'application/json');
  if (token) headers.set('Authorization', `Bearer ${token}`);
  const res = await fetch(API + path, { ...options, headers });
  if (res.status === 401) {
    localStorage.removeItem('token');
    window.location.href = '/login';
  }
  return res;
}

export const api = {
  async login(username: string, password: string) {
    const r = await fetch(API + '/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });
    if (!r.ok) throw new Error('bad_credentials');
    return r.json() as Promise<{ token: string }>;
  },
  async tasksList() {
    const r = await request('/api/tasks');
    return r.json();
  },
  async tasksCreate(title: string) {
    const r = await request('/api/tasks', { method: 'POST', body: JSON.stringify({ title }) });
    return r.json();
  },
  async tasksUpdate(id: number, title: string, done: boolean) {
    const r = await request(`/api/tasks/${id}`, { method: 'PUT', body: JSON.stringify({ title, done }) });
    return r.json();
  },
  async tasksDelete(id: number) {
    await request(`/api/tasks/${id}`, { method: 'DELETE' });
  }
};
