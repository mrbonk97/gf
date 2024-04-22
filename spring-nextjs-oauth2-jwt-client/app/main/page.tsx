"use client";
import { useAuth } from "@/context/auth-context";
import { useRouter } from "next/navigation";

const MainPage = () => {
  const auth = useAuth();
  const router = useRouter();
  const user = auth.user;

  if (auth.isLoggedIn === false) {
    router.push("/");
    return;
  }

  const handleSignOut = () => {
    auth.setIsLoggedIn(false);
    auth.setUser(null);
    router.push("/");
  };

  return (
    <main className="bg-slate-200 w-full h-full flex items-center justify-center flex-col">
      <article className="w-96 h-96 bg-white rounded-2xl text-lg font-semibold p-10 space-y-5">
        <h1 className="text-center">User Info</h1>
        <ul className="space-y-2">
          {user?.imageUrl && (
            <li className="flex items-center justify-center">
              <img
                src={user?.imageUrl}
                alt="avatar"
                width={100}
                height={100}
                className="rounded-full"
              />
            </li>
          )}
          <li>UserId: {user?.id}</li>
          <li>UserEmail: {user?.email}</li>
          <li>Name: {user?.name}</li>
        </ul>
        <button
          onClick={handleSignOut}
          className="bg-slate-200 px-5 py-1 rounded-2xl"
        >
          Sign Out
        </button>
      </article>
    </main>
  );
};

export default MainPage;
