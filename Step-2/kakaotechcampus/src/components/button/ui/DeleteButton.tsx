"use client";

import { DELETE_GIFT } from "@/widgets/gift/api/gift.action";
import { useRouter } from "next/navigation";
import React from "react";

function DeleteButton({ id }: { id: number }) {
  const router = useRouter();
  const onClickDelete = async () => {
    const res = await DELETE_GIFT(id);
    if (res.status === 204) {
      alert(`상품 ${id}번을 삭제하였습니다.`);
      router.refresh();
    } else {
      alert("상품 삭제에 실패하였습니다.");
    }
  };
  return (
    <button
      type="button"
      className="min-w-24 h-10 flex justify-center items-center rounded-md shadow-sm duration-200 bg-red-300 hover:bg-red-200 z-10"
      onClick={onClickDelete}
    >
      <span className="font-medium text-sm text-black">삭제</span>
    </button>
  );
}

export { DeleteButton };
