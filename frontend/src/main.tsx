function isAuthed(){ return !!localStorage.getItem('token'); }
const path = window.location.pathname;
createRoot(document.getElementById('root')!).render(
  path.startsWith('/login') ? <Login/> : (isAuthed() ? <App/> : <Login/>)
);