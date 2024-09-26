const QrView = ({ img }: QrViewProps) => {
  return (
    <div className="mt-4 flex h-[48dvh] justify-center">
      <img src={img} />
    </div>
  );
};

export default QrView;