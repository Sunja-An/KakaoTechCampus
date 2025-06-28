import { DeleteButton, ModifyButton } from "@/components/button";
import Link from "next/link";
import React from "react";

type GiftColumnType = {
  id: number;
  giftId: number;
  giftName: string;
  giftPrice: number;
  giftPhotoUrl: string;
};

function GiftColumn({
  id,
  giftId,
  giftName,
  giftPrice,
  giftPhotoUrl,
}: GiftColumnType) {
  return (
    <Link href={`/gift/${id}`} className="w-full">
      <div className="box-border py-4 px-8 w-full flex justify-between items-center rounded-md gap-4 border-b border-gray-200 duration-200 hover:bg-gray-100">
        <div className="w-1/5 flex justify-center items-center">
          <span className="font-semibold text-lg text-black">{giftId}</span>
        </div>
        <div className="w-1/5 flex justify-center items-center">
          <span className="font-medium text-sm text-black">{giftName}</span>
        </div>
        <div className="w-1/5 flex justify-center items-center">
          <span className="font-medium text-sm text-black">{giftPrice}</span>
        </div>
        <div className="w-1/5 flex justify-center items-center">
          <span className="font-medium text-sm text-black">{giftPhotoUrl}</span>
        </div>
        <div className="w-1/5 flex justify-center items-center gap-4">
          <ModifyButton />
          <DeleteButton />
        </div>
      </div>
    </Link>
  );
}

export { GiftColumn };
