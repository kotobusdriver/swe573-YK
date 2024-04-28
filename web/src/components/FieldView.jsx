
function FieldView({field, template}) {

    const fieldDefinition = template.fields.find((f) => field.templateFieldId === f.id);

    function renderText() {
        return (
            <p>{field.data}</p>
        );
    }

    function renderImage() {
        return (
            <img src={field.data}/>
        );
    }

    function renderAttachment() {
        return (
            <a href={field.data}>Download File</a>
        );
    }

    function renderDate() {
        return (
            <p>{field.data}</p>
        );
    }

    function renderUnknown() {
        return (
            <p>Unknown type for field</p>
        );
    }
    function renderInput() {
        switch (fieldDefinition.type) {
            case "TEXT" :
                return renderText();
            case "IMAGE" :
                return renderImage();
            case "ATTACHMENT" :
                return renderAttachment();
            case "DATE" :
                return renderDate();
            default:
                return renderUnknown();
        }
    }

    return (
        <>
            <div className="card m-2 w-25">
                {
                    renderInput()
                }
            </div>
        </>
    )
}

export default FieldView
