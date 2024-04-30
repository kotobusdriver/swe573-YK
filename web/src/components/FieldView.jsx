
function FieldView({field, template}) {

    const fieldDefinition = template.fields.find((f) => field.templateFieldId === f.id);

    function renderText() {
        return (
            <p>{fieldDefinition.name}: {field.data}</p>
        );
    }

    function renderImage() {
        return (
            <>
                <p>{fieldDefinition.name}</p>
                <img src={field.data} className="img-fluid"/>
            </>
        );
    }

    function renderAttachment() {
        return (
            <>
                <p>{fieldDefinition.name}</p>
                <a href={field.data}>Download File</a>
            </>
        );
    }

    function renderDate() {
        return (
            <p>{fieldDefinition.name}: {field.data}</p>
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
            <div className="p-2">
                {
                    renderInput()
                }
            </div>
        </>
    )
}

export default FieldView
