import { GithubBanner } from "@/components/banner";
import Image from "next/image";
import Link from "next/link";
import React from "react";

type AssignmentCardType = {
  previewPhoto?: string;
  title: string;
  startPeriod: string;
  endPeriod: string;
  githubLink: string;
};

function AssignmentCard({
  previewPhoto,
  title,
  startPeriod,
  endPeriod,
  githubLink,
}: AssignmentCardType) {
  return (
    <Link href={githubLink}>
      <div className="box-border p-6 min-w-60 min-h-80 flex flex-col justify-start items-start rounded-xl shadow-lg gap-4 border duration-200 border-gray-100 hover:bg-gray-200">
        <div className="w-full rounded-sm overflow-hidden">
          {previewPhoto ? (
            <Image src={previewPhoto} alt="preview" />
          ) : (
            <GithubBanner />
          )}
        </div>
        <div className="w-full flex flex-col justify-start items-start">
          <h4 className="text-lg text-black font-bold">{title}</h4>
          <span className="text-sm text-gray-500 font-semibold">
            {startPeriod} ~ {endPeriod}
          </span>
        </div>
      </div>
    </Link>
  );
}

export { AssignmentCard };
