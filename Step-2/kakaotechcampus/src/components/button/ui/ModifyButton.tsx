import Link from "next/link";
import React from "react";

function ModifyButton({ id }: { id: number }) {
  return (
    <Link href={`/gift/${id}/edit`}>
      <button
        type="button"
        className="min-w-24 h-10 flex justify-center items-center rounded-md shadow-sm duration-200 bg-green-300 hover:bg-green-200 z-10"
      >
        <span className="font-medium text-sm text-black">수정</span>
      </button>
    </Link>
  );
}

export { ModifyButton };
