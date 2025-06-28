import Link from "next/link";
import React from "react";

function MainHeader() {
  return (
    <header className="sticky top-0 left-0 box-border px-20 w-full h-20 flex justify-between items-center border-b-1 border-gray-300 bg-[#fffafa] z-50">
      <div className="min-w-4 min-h-4">
        <Link href={"/"}>
          <h1 className="text-xl text-black font-bold">KTC 3기</h1>
        </Link>
      </div>
      <div className="logo"></div>
      <nav className="min-w-60 h-full">
        <ul className="w-full h-full flex justify-around items-center">
          <li className="text-sm text-black font-semibold">Step 1</li>
          <li className="text-sm text-black font-semibold">Step 2</li>
          <li className="text-sm text-black font-semibold">Step 3</li>
        </ul>
      </nav>
    </header>
  );
}

export { MainHeader };
