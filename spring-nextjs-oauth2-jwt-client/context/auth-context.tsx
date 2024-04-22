"use client";
import { createContext, useContext, useState } from "react";

type User = {
  id: string;
  email: string;
  name: string;
  imageUrl: string | null;
};

interface AuthContextProps {
  user: User | null;
  setUser: React.Dispatch<User | null>;
  isLoggedIn: boolean;
  setIsLoggedIn: React.Dispatch<boolean>;
}

const AuthContext = createContext<AuthContextProps>({
  user: null,
  setUser: () => {},
  isLoggedIn: false,
  setIsLoggedIn: () => {},
});

export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const values = { user, setUser, isLoggedIn, setIsLoggedIn };

  return <AuthContext.Provider value={values}>{children}</AuthContext.Provider>;
};
