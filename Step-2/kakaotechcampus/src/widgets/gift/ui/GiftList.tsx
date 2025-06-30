import { GiftColumn } from "@/components";
import React, { Suspense } from "react";
import { type ResponseGiftDto } from "@/widgets/gift/types/gift";

type GiftListType = {
  data: ResponseGiftDto[];
};

function GiftList({ data }: GiftListType) {
  return (
    <Suspense fallback={<h1>Loading gifts...</h1>}>
      <div className="box-border p-4 w-full flex flex-col justify-start items-start rounded-md shadow-md gap-4">
        <div className="box-border py-4 px-8 w-full flex justify-between items-center rounded-md gap-4 border-b border-gray-200">
          <div className="w-1/5 flex justify-center items-center">
            <span className="font-semibold text-lg text-black">상품 ID</span>
          </div>
          <div className="w-1/5 flex justify-center items-center">
            <span className="font-medium text-sm text-black">상품 명</span>
          </div>
          <div className="w-1/5 flex justify-center items-center">
            <span className="font-medium text-sm text-black">상품 가격</span>
          </div>
          <div className="w-1/5 flex justify-center items-center">
            <span className="font-medium text-sm text-black">
              상품 사진 URL
            </span>
          </div>
          <div className="w-1/5 flex justify-center items-center">
            <span className="font-medium text-sm text-black">OPTION</span>
          </div>
        </div>
        {data.length !== 0 ? (
          data.map((item: ResponseGiftDto, key: number) => {
            return (
              <GiftColumn
                id={item.id}
                giftId={item.giftId}
                giftName={item.giftName}
                giftPhotoUrl={item.giftPhotoUrl}
                giftPrice={item.giftPrice}
                key={key}
              />
            );
          })
        ) : (
          <div>
            <h1 className="">상품이 없습니다..</h1>
          </div>
        )}
      </div>
    </Suspense>
  );
}

export { GiftList };
