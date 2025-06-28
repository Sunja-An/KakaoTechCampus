import { AssignmentCard } from "@/components";

export default function Home() {
  return (
    <main className="w-screen h-screen flex flex-col justify-start items-start">
      <div className="box-border p-10 w-full flex flex-col justify-start items-center gap-8">
        <div className="w-full flex flex-col justify-center items-center">
          <h1 className="text-3xl text-black font-bold">
            카카오 테크 캠퍼스 3기
          </h1>
          <h3 className="text-base text-black font-medium">
            2025.04.01 ~ 2025.11.30
          </h3>
        </div>
        <section className="w-full flex flex-nowrap justify-center items-center gap-8">
          <AssignmentCard
            title="숙제1"
            startPeriod="2025-04-01"
            endPeriod="2025-05-01"
            githubLink=""
          />
          <AssignmentCard
            title="숙제2"
            startPeriod="2025-04-01"
            endPeriod="2025-05-01"
            githubLink=""
          />
          <AssignmentCard
            title="Spring Gift Product"
            startPeriod="2025-06-23"
            endPeriod="2025-06-30"
            githubLink="https://github.com/next-step/spring-gift-product"
          />
        </section>
        <section className=""></section>
      </div>
    </main>
  );
}
