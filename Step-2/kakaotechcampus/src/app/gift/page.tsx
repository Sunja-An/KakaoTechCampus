import React from "react";

import { GET_GIFTS, GiftList, type ResponseGiftDto } from "@/widgets";
import { AddButton } from "@/components";

export default async function GiftListPage() {
  const data: ResponseGiftDto[] = await GET_GIFTS();
  if (!data) {
    return <h1>Loading gifts...</h1>;
  }
  return (
    <div className="relative w-full flex flex-col justify-start items-start gap-8">
      <section className="w-full flex flex-col justify-center items-center gap-4">
        <h1 className="font-bold text-3xl text-black">선물 CRUD 과제</h1>
        <span className="font-medium text-lg text-black">
          2025.06.23 ~ 2025.06.30
        </span>
      </section>
      <section className="box-border px-20 w-full flex flex-col justify-start items-center gap-8">
        <div className="px-10 w-full flex justify-end items-center">
          <AddButton />
        </div>
        <div className="w-full flex flex-col justify-start items-center">
          <GiftList data={data} />
        </div>
      </section>
    </div>
  );
}
