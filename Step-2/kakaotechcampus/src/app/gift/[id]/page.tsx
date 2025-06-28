import { DeleteButton, ModifyButton } from "@/components";
import { GET_GIFT, ResponseGiftDto } from "@/widgets";
import React from "react";

export default async function page({ params }: { params: { id: number } }) {
  const { id } = params;

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
    <div className="w-full">
      <section className="w-full flex flex-col justify-start items-center">
        {data ? (
          <>
            <span className="">{data.giftId}</span>
            <span className="">{data.giftName}</span>
            <span className="">{data.giftPrice}</span>
            <span className="">{data.giftPhotoUrl}</span>
          </>
        ) : (
          <div>
            <span className="font-bold text-4xl text-black">
              🥲 상품 조회가 되지 않습니다.
            </span>
          </div>
        )}
      </section>
      <section className="w-full flex justify-center items-center">
        <ModifyButton />
        <DeleteButton />
      </section>
    </div>
  );
}
