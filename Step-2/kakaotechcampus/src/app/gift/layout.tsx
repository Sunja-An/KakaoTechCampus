import React from "react";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <main className="box-border p-10 w-screen h-screen flex justify-start items-start">
      {children}
    </main>
  );
}
