import React from "react";
import GithubMark from "/public/svg/Github.svg";

type IconType = {
  width?: string;
  height?: string;
  fill?: string;
};

function GithubIcon(props: IconType) {
  return <GithubMark {...props} />;
}

export { GithubIcon };
