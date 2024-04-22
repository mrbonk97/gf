"use client";

import { useAuth } from "@/context/auth-context";
import { useSearchParams } from "next/navigation";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

const OAuth2RedirectPage = () => {
  const auth = useAuth();
  const router = useRouter();
  const email = useSearchParams().get("email")!;
  const name = useSearchParams().get("name")!;
  const id = useSearchParams().get("id")!;
  const imageUrl = useSearchParams().get("image_url");
  const accessToken = useSearchParams().get("access_token");

  useEffect(() => {
    localStorage.setItem("access_token", accessToken!);
    router.push("/main");
  }, [auth.user]);

  auth.setUser({ id, email, name, imageUrl });
  auth.setIsLoggedIn(true);

  return <main>redirect</main>;
};

export default OAuth2RedirectPage;
