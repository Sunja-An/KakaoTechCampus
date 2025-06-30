import { GET_GIFT, GiftEditForm, type ResponseGiftDto } from "@/widgets";
import React from "react";

export default async function page({
  params,
}: {
  params: Promise<{ id: number }>;
}) {
  const { id } = (await params) ?? {
    id: 0,
  };

  if (id === undefined) {
    return (
      <div className="w-full flex justify-center items-center">
        <span className="font-bold text-4xl text-black">
          🥲 URL 이 잘못되었습니다.
        </span>
      </div>
    );
  }

  const data: ResponseGiftDto = await GET_GIFT(id);

  return (
    <div className="w-full flex flex-col justify-start items-start">
      <div className="w-full flex justify-center items-center">
        <h1 className="font-bold text-3xl text-black">상품 수정</h1>
      </div>
      <GiftEditForm props={data} />
    </div>
  );
}
