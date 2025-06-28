import React from "react";

import { GiftForm } from "@/widgets";

export default function page() {
  return (
    <div className="w-full flex flex-col justify-start items-start">
      <div className="w-full flex justify-center items-center">
        <h1 className="font-bold text-3xl text-black">상품 생성</h1>
      </div>
      <GiftForm />
    </div>
  );
}
