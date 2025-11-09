export const API_URL = import.meta.env.VITE_API_URL ?? '';
export async function apiFetch(path: string, options: RequestInit = {}) {
  const token = localStorage.getItem('token');
  const headers = new Headers(options.headers);
  if (token) headers.set('Authorization', `Bearer ${token}`);
  headers.set('Content-Type', 'application/json');
  const r = await fetch(API_URL + path, { ...options, headers });
  if (r.status === 401) window.location.href = '/login';
  return r;
}
