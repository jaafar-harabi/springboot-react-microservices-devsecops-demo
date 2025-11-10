import React from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import Login from './Login';

const token = localStorage.getItem('token');
const path = location.pathname;

createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    {path.startsWith('/login') ? <Login/> : (token ? <App/> : <Login/>)}
  </React.StrictMode>
);
import React from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import Login from './Login';

const token = localStorage.getItem('token');
const path = location.pathname;

createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    {path.startsWith('/login') ? <Login/> : (token ? <App/> : <Login/>)}
  </React.StrictMode>
);
