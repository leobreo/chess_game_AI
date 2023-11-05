{ pkgs ? import <nixpkgs> { } }:

with pkgs;

mkShell { buildInputs = [ (maven.override { jdk = jdk11; }) ]; }
