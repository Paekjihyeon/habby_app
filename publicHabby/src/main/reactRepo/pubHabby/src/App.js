import './App.css';
import React, { useEffect, useState } from "react";
import { NavLink, Route, Routes } from "react-router-dom";
import UserLogin from './component/login/login';
import Test from './component/test';
// import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

// const queryClient = new QueryClient();
function App() {
  return (
      <Routes>
        <Route path="/" element={<Test />} />
        <Route path="/login" element={<UserLogin />} />
      </Routes>
  );
}

export default App;
