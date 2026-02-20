/*
This line prints message in browser console

Purpose:
To confirm that content script is successfully loaded

Content script runs inside Gmail webpage
*/
console.log("Email Writer Extension - Content Script Loaded");



/*
Function to create AI Reply Button

This function dynamically creates a button element
which will be added to Gmail compose toolbar
*/
function createAIButton() {

   // Create new div element
   const button = document.createElement('div');


   /*
   These class names are Gmail default button classes

   This makes our button look like Gmail native button

   Without this, button will look different
   */
   button.className = 'T-I J-J5-Ji aoO v7 T-I-atl L3';


   // Adds space between buttons
   button.style.marginRight = '8px';


   // Text displayed on button
   button.innerHTML = 'AI Reply';


   /*
   role="button"

   Accessibility purpose

   Helps screen readers understand element is button
   */
   button.setAttribute('role','button');


   /*
   Tooltip text

   Shows when user hovers mouse
   */
   button.setAttribute('data-tooltip','Generate AI Reply');


   // return button element
   return button;
}




/*
Function to get email content from Gmail

This reads original email text
*/
function getEmailContent() {

    /*
    Different Gmail selectors

    Gmail uses different classes for email body

    So we try multiple selectors
    */
    const selectors = [

        '.h7',

        '.a3s.aiL',

        '.gmail_quote',

        '[role="presentation"]'
    ];


    /*
    Loop through selectors
    */
    for (const selector of selectors) {


        // find element
        const content = document.querySelector(selector);


        // if found
        if (content) {

            // return email text
            return content.innerText.trim();
        }


        // if not found return empty
        return '';
    }
}




/*
Function to find Gmail compose toolbar

Toolbar contains send button, formatting etc
*/
function findComposeToolbar() {


    const selectors = [

        '.btC',

        '.aDh',

        '[role="toolbar"]',

        '.gU.Up'
    ];


    for (const selector of selectors) {


        // find toolbar
        const toolbar = document.querySelector(selector);


        if (toolbar) {

            // return toolbar
            return toolbar;
        }


        return null;
    }
}




/*
Function to inject AI button into Gmail UI
*/
function injectButton() {


    /*
    Check if button already exists

    Prevent duplicate buttons
    */
    const existingButton = document.querySelector('.ai-reply-button');


    if (existingButton) existingButton.remove();




    /*
    Find toolbar
    */
    const toolbar = findComposeToolbar();


    if (!toolbar) {

        console.log("Toolbar not found");

        return;
    }



    console.log("Toolbar found, creating AI button");



    /*
    Create button
    */
    const button = createAIButton();



    /*
    Add custom class

    Used to identify button later
    */
    button.classList.add('ai-reply-button');




    /*
    Add click event

    This runs when user clicks AI Reply button
    */
    button.addEventListener('click', async () => {


        try {

            /*
            Change button text while loading
            */
            button.innerHTML = 'Generating...';


            // disable button
            button.disabled = true;




            /*
            Get original email content
            */
            const emailContent = getEmailContent();




            /*
            Call Spring Boot API

            This sends POST request to backend
            */
            const response = await fetch('http://localhost:8080/api/email/generate', {


                method: 'POST',


                headers: {

                    'Content-Type': 'application/json',
                },


                /*
                Send email content and tone
                */
                body: JSON.stringify({

                    emailContent: emailContent,

                    tone: "professional"
                })
            });




            /*
            Check if API failed
            */
            if (!response.ok) {

                throw new Error('API Request Failed');
            }




            /*
            Get generated reply
            */
            const generatedReply = await response.text();




            /*
            Find Gmail compose text box
            */
            const composeBox = document.querySelector('[role="textbox"][g_editable="true"]');




            if (composeBox) {


                // focus textbox
                composeBox.focus();



                /*
                Insert generated text into Gmail
                */
                document.execCommand('insertText', false, generatedReply);

            } else {

                console.error('Compose box was not found');
            }


        } catch (error) {


            console.error(error);


            alert('Failed to generate reply');
        }


        finally {


            /*
            Reset button text
            */
            button.innerHTML = 'AI Reply';


            button.disabled =  false;
        }
    });




    /*
    Insert button into toolbar

    insertBefore → insert at first position
    */
    toolbar.insertBefore(button, toolbar.firstChild);
}





/*
MutationObserver

This watches DOM changes

Gmail dynamically loads compose window

So we detect when compose window appears
*/
const observer = new MutationObserver((mutations) => {




    for(const mutation of mutations) {




        const addedNodes = Array.from(mutation.addedNodes);




        /*
        Check if compose window opened
        */
        const hasComposeElements = addedNodes.some(node =>

            node.nodeType === Node.ELEMENT_NODE && 

            (

                node.matches('.aDh, .btC, [role="dialog"]')

                ||

                node.querySelector('.aDh, .btC, [role="dialog"]')

            )
        );




        /*
        If compose found
        */
        if (hasComposeElements) {


            console.log("Compose Window Detected");



            /*
            Wait 500ms then inject button
            */
            setTimeout(injectButton, 500);
        }
    }
});




/*
Start observing entire document
*/
observer.observe(document.body, {


    childList: true,


    subtree: true
});