import Link from "next/link";

export default function Home() {
  const googleUrl = process.env.GOOGLE_AUTH_URL!;
  const naverUrl = process.env.NAVER_AUTH_URL!;
  return (
    <main className="h-full w-full lg:bg-slate-200 flex flex-col items-center justify-center">
      <h1 className="text-4xl text-zinc-700 font-semibold">Sign up</h1>
      <section className="text-white text-4xl bg-white rounded-2xl flex flex-col items-center justify-center gap-5 p-10 mt-10">
        <Link
          className="w-60 py-2 rounded-2xl bg-zinc-800 text-center font-semibold"
          href={googleUrl}
        >
          Google
        </Link>
        <Link
          className="w-60 py-2 rounded-2xl bg-green-500  text-center font-semibold"
          href={naverUrl}
        >
          Naver
        </Link>
      </section>
    </main>
  );
}
