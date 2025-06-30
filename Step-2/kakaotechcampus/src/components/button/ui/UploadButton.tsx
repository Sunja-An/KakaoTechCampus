import React from "react";

type UploadButtonType = {
  onClick: () => Promise<void>;
};

function UploadButton({ onClick }: UploadButtonType) {
  return (
    <button
      type="button"
      onClick={onClick}
      className="min-w-24 h-10 flex justify-center items-center rounded-md shadow-sm duration-200 bg-blue-300 hover:bg-blue-100"
    >
      <span className="font-medium text-sm text-black">상품 업로드</span>
    </button>
  );
}

export { UploadButton };
