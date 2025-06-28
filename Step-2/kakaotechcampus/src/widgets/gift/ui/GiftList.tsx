import { GiftColumn } from "@/components";
import React from "react";

function GiftList() {
  return (
    <div className="box-border p-4 w-full flex flex-col justify-start items-start border border-gray-300 rounded-md gap-4">
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
          <span className="font-medium text-sm text-black">상품 사진 URL</span>
        </div>
        <div className="w-1/5 flex justify-center items-center">
          <span className="font-medium text-sm text-black">OPTION</span>
        </div>
      </div>
      <GiftColumn
        id={1}
        giftId={2040}
        giftName="기프트입니다."
        giftPhotoUrl="test"
        giftPrice={30000}
      />
    </div>
  );
}

export { GiftList };
