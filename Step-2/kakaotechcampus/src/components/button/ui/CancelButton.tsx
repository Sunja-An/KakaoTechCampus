import Link from "next/link";
import React from "react";

function CancelButton() {
  return (
    <Link href={"/gift"}>
      <button
        type="button"
        className="min-w-24 h-10 flex justify-center items-center rounded-md shadow-sm duration-200 bg-red-300 hover:bg-red-200 z-10"
      >
        <span className="font-medium text-sm text-black">돌아가기</span>
      </button>
    </Link>
  );
}

export { CancelButton };
