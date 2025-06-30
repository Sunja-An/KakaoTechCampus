import { AssignmentCard } from "@/components";
import React from "react";

export default function page() {
  return (
    <main className="w-screen h-screen flex flex-col justify-start items-start">
      <div className="box-border p-10 w-full flex flex-col justify-start items-center gap-8">
        <div className="w-full flex flex-col justify-center items-center">
          <h1 className="text-3xl text-black font-bold">
            카카오 테크 캠퍼스 3기 STEP#2
          </h1>
          <h3 className="text-base text-black font-medium">
            2025.06.23 ~ 2025.08.01
          </h3>
        </div>
        <section className="w-full flex flex-nowrap justify-center items-center gap-8">
          <AssignmentCard
            title="상품 관리 시스템"
            startPeriod="2025-06-23"
            endPeriod="2025-06-30"
            githubLink="/gift"
          />
        </section>
        <section className=""></section>
      </div>
    </main>
  );
}
