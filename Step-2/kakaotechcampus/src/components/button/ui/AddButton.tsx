import Link from "next/link";
import React from "react";

function AddButton() {
  return (
    <Link href={"/gift/create"}>
      <button
        type="button"
        className="min-w-24 h-10 flex justify-center items-center rounded-md shadow-sm duration-200 bg-blue-300 hover:bg-blue-100"
      >
        <span className="font-medium text-sm text-black">상품 생성</span>
      </button>
    </Link>
  );
}

export { AddButton };
