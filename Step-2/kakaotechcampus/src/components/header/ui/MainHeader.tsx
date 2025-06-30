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
          <Link href={"/first"}>
            <li className="px-4 py-2 text-sm text-black font-semibold duration-200 hover:bg-gray-300 rounded-md">
              Step 1
            </li>
          </Link>
          <Link href={"/second"}>
            <li className="px-4 py-2 text-sm text-black font-semibold duration-200 hover:bg-gray-300 rounded-md">
              Step 2
            </li>
          </Link>
          <Link href={"/third"}>
            <li className="px-4 py-2 text-sm text-black font-semibold duration-200 hover:bg-gray-300 rounded-md">
              Step 3
            </li>
          </Link>
        </ul>
      </nav>
    </header>
  );
}

export { MainHeader };
